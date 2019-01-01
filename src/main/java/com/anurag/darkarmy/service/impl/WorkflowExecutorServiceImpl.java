package com.anurag.darkarmy.service.impl;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.common.util.AggregationUtil;
import com.anurag.darkarmy.common.util.collection.Tuple2;
import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.TaskDef;
import com.anurag.darkarmy.core.domain.WorkflowDef;
import com.anurag.darkarmy.execution.StandardTopologyOptimizer;
import com.anurag.darkarmy.service.DefinitionService;
import com.anurag.darkarmy.service.TaskExecutorService;
import com.anurag.darkarmy.service.WorkflowExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class WorkflowExecutorServiceImpl implements WorkflowExecutorService {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final TaskExecutorService taskExecutorService;
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    private final DefinitionService definitionService;

    public WorkflowExecutorServiceImpl(TaskExecutorService service, DefinitionService definitionService) {
        this.taskExecutorService = service;
        this.definitionService = definitionService;
    }

    @Override
    public Map execute(String workflowName, AggregationContext context) {
        WorkflowDef workflow = definitionService.fetchWorkflow(workflowName, 1).orElseThrow(() -> new SystemException("No workflow with ID '" + workflowName + "'"));
        List<TaskDef> tasks = getWorkflowTasks(workflow);
        return execute(workflow, tasks, context);
    }

    private List<TaskDef> getWorkflowTasks(WorkflowDef workflow) {
        List<TaskDef> executableTasks = new ArrayList<>();
        for (String taskId : workflow.getTasks()) {
            executableTasks.addAll(getConnectedTasks(taskId));
        }

        return executableTasks;
    }

    private List<TaskDef> getConnectedTasks(String taskId) {
        List<TaskDef> tasks = new ArrayList<>();
        TaskDef def = definitionService.fetchTask(taskId).orElseThrow(() -> new SystemException("No task with ID '" + taskId + "'"));
        tasks.add(def);
        if (def.getRequires() != null) {
            for (String connectedTask : def.getRequires()) {
                tasks.addAll(getConnectedTasks(connectedTask));
            }
        }
        return tasks;
    }

    @Override
    public Map execute(WorkflowDef workflow, List<TaskDef> tasks, AggregationContext context) {
        List<List<TaskDef>> plan = computeExecutionPlan(tasks);

        for (List<TaskDef> group : plan) {
            executeTasksConcurrently(group, context).forEach(t -> {
                extractTaskResultAndUpdateContext(context, t);
            });
        }

        return AggregationUtil.buildOutput(workflow.getOutput(), context.getContext());
    }

    @SuppressWarnings("unchecked")
    private void extractTaskResultAndUpdateContext(AggregationContext context, Tuple2<TaskDef, Future<Map>> t) {
        try {
            Map result = t.getSecond().get();
            context.putContext(t.getFirst().getId(), result);
        } catch (InterruptedException | ExecutionException ex) {
            log.error("Exception executing task '{}'", t.getFirst().getId(), ex);
        }
    }

    private List<Tuple2<TaskDef, Future<Map>>> executeTasksConcurrently(final List<TaskDef> tasks, final AggregationContext context) {
        return tasks.stream()
                .map(task -> Tuple2.valueOf(task, pool.submit(() -> executeTask(task, context))))
                .collect(Collectors.toList());
    }

    private Map executeTask(TaskDef task, AggregationContext context) {
        return taskExecutorService.execute(task, context);
    }

    private List<List<TaskDef>> computeExecutionPlan(List<TaskDef> tasks) {
        return new StandardTopologyOptimizer(tasks).computeExecutionPlan();
    }
}
