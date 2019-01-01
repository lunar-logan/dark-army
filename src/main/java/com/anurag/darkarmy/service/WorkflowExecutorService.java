package com.anurag.darkarmy.service;

import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.TaskDef;
import com.anurag.darkarmy.core.domain.WorkflowDef;

import java.util.List;
import java.util.Map;

public interface WorkflowExecutorService {
    Map execute(String workflow, AggregationContext context);

    Map execute(WorkflowDef workflow, List<TaskDef> tasks, AggregationContext context);
}
