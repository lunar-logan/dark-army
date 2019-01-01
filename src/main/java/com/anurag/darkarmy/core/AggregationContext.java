package com.anurag.darkarmy.core;

import com.anurag.darkarmy.common.util.CollectionUtil;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class AggregationContext {
    private final String correlationId;
    private final long startTime;
    private final Map<String, Map<String, Object>> context = new ConcurrentHashMap<>();

    public AggregationContext(String correlationId, long startTime) {
        this.correlationId = correlationId;
        this.startTime = startTime;
    }

    public AggregationContext(String correlationId) {
        this(correlationId, System.nanoTime());
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public Map<String, Object> getContext(String dependencyId) {
        return context.get(dependencyId);
    }

    public Map<String, Object> getContext() {
        return Collections.unmodifiableMap(context);
    }

    public void removeContext(String dependencyId) {
        context.remove(dependencyId);
    }

    public void putContext(String taskId, Map<String, Object> context) {
        this.context.put(taskId, context);
    }

    public void updateContext(String dependencyId, Map<String, Object> additionalContext) {
        if (additionalContext != null) {
            synchronized (context) {
                Map<String, Object> oldContext = context.get(dependencyId);
                if (oldContext != null) {
                    context.put(dependencyId, Collections.unmodifiableMap(CollectionUtil.merge(oldContext, additionalContext)));
                } else {
                    context.put(dependencyId, Collections.unmodifiableMap(additionalContext));
                }
            }
        }
    }

    public long getStartTime() {
        return startTime;
    }
}
