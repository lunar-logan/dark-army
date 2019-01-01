package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.TaskDef;
import com.anurag.darkarmy.persistence.entity.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskToTaskDefConverter implements Converter<Task, TaskDef> {

    private final CacheToCacheDefConverter cacheToCacheDefConverter;
    private final LimitToLimitDefConverter limitToLimitDefConverter;
    private final RetryToRetryDefConverter retryToRetryDefConverter;
    private final OutputToOutputDefConverter outputToOutputDefConverter;

    public TaskToTaskDefConverter(CacheToCacheDefConverter cacheToCacheDefConverter, LimitToLimitDefConverter limitToLimitDefConverter, RetryToRetryDefConverter retryToRetryDefConverter, OutputToOutputDefConverter outputToOutputDefConverter) {
        this.cacheToCacheDefConverter = cacheToCacheDefConverter;
        this.limitToLimitDefConverter = limitToLimitDefConverter;
        this.retryToRetryDefConverter = retryToRetryDefConverter;
        this.outputToOutputDefConverter = outputToOutputDefConverter;
    }

    @Override
    public TaskDef convert(Task source) {
        TaskDef def = new TaskDef();
        def.setId(source.getId());
        def.setDescription(source.getDescription());
        def.setRequires(source.getRequires());
        def.setEndpoint(source.getEndpoint());
        if (source.getCache() != null) {
            def.setCache(cacheToCacheDefConverter.convert(source.getCache()));
        }
        if (source.getLimits() != null) {
            def.setLimits(limitToLimitDefConverter.convert(source.getLimits()));
        }
        if (source.getRetry() != null) {
            def.setRetry(retryToRetryDefConverter.convert(source.getRetry()));
        }
        if (source.getOutput() != null) {
            def.setOutput(outputToOutputDefConverter.convert(source.getOutput()));
        }
        return def;
    }
}
