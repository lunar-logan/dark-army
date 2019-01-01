package com.anurag.darkarmy.execution;

import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.TaskDef;

import java.util.Map;
import java.util.concurrent.Callable;

public class AsyncTaskExecutor implements Callable<Map> {

    private final TaskDef task;
    private final AggregationContext context;

    public AsyncTaskExecutor(TaskDef task, AggregationContext context) {
        this.task = task;
        this.context = context;
    }

    @Override
    public Map call() throws Exception {

        return null;
    }
}
