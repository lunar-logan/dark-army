package com.anurag.darkarmy.service;

import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.TaskDef;

import java.util.Map;

public interface TaskExecutorService {
    Map execute(TaskDef task, AggregationContext context);
}
