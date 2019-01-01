package com.anurag.darkarmy.execution;

import com.anurag.darkarmy.core.domain.TaskDef;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StandardTopologyOptimizer implements Optimizer {
    private final List<TaskDef> actions;
    private final AtomicInteger ctr = new AtomicInteger(0);
    private final Map<Integer, Set<Integer>> dag = new HashMap<>();
    private final Map<String, Integer> nodeMapping = new HashMap<>();
    private final Map<Integer, TaskDef> invNodeMapping = new HashMap<>();

    public StandardTopologyOptimizer(final List<TaskDef> actions) {
        this.actions = actions;
    }

    @Override
    public List<List<TaskDef>> computeExecutionPlan() {
        preProcess();
        computeDependencyGraph();
        List<Integer> sortedOrder = topologicalSort();
        if (sortedOrder != null) {
            return sortedOrder
                    .stream()
                    .map(invNodeMapping::get)
                    .map(Arrays::asList)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private void preProcess() {
        if (actions != null) {
            for (TaskDef action : actions) {
                Integer id = nodeMapping.computeIfAbsent(action.getId(), k -> ctr.incrementAndGet());
                invNodeMapping.put(id, action);
            }
        }
    }

    private List<Integer> topologicalSort() {
        List<Integer> sortedNodes = new ArrayList<>();
        Set<Integer> permanentMark = new HashSet<>();
        Set<Integer> temporaryMark = new HashSet<>();
        for (Integer u : dag.keySet()) {
            if (!permanentMark.contains(u)) {
                visit(u, sortedNodes, permanentMark, temporaryMark);
            }
        }
        return sortedNodes;
    }

    private void visit(int u,
                       List<Integer> sortedList,
                       Set<Integer> permanentMark,
                       Set<Integer> temporaryMark) {
        if (permanentMark.contains(u)) return;
        if (temporaryMark.contains(u)) throw new RuntimeException("Cycle detected");
        temporaryMark.add(u);
        for (int v : dag.getOrDefault(u, Collections.emptySet())) {
            visit(v, sortedList, permanentMark, temporaryMark);
        }
        permanentMark.add(u);
        sortedList.add(u);
    }

    private void computeDependencyGraph() {
        if (actions != null) {
            for (TaskDef action : actions) {
                Set<Integer> adjList = dag.computeIfAbsent(nodeMapping.get(action.getId()), k -> new HashSet<>());
                if (action.getRequires() != null) {
                    action.getRequires()
                            .stream()
                            .map(nodeMapping::get)
                            .forEach(adjList::add);
                }
            }
        }
    }
}
