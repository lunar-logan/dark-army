package com.anurag.darkarmy.execution;

import com.anurag.darkarmy.core.domain.TaskDef;

import java.util.List;

public interface Optimizer {
    List<List<TaskDef>> computeExecutionPlan();
}
