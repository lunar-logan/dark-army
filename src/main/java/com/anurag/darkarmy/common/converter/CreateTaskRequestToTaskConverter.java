package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.request.CreateTaskRequest;
import com.anurag.darkarmy.persistence.entity.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskRequestToTaskConverter implements Converter<CreateTaskRequest, Task> {

    private final OutputDefToOutputConverter outputDefToOutputConverter;
    private final LimitDefToLimitConverter limitDefToLimitConverter;
    private final CacheDefToCacheConverter cacheDefToCacheConverter;
    private final RetryDefToRetryConverter retryDefToRetryConverter;

    public CreateTaskRequestToTaskConverter(OutputDefToOutputConverter outputDefToOutputConverter, LimitDefToLimitConverter limitDefToLimitConverter, CacheDefToCacheConverter cacheDefToCacheConverter, RetryDefToRetryConverter retryDefToRetryConverter) {
        this.outputDefToOutputConverter = outputDefToOutputConverter;
        this.limitDefToLimitConverter = limitDefToLimitConverter;
        this.cacheDefToCacheConverter = cacheDefToCacheConverter;
        this.retryDefToRetryConverter = retryDefToRetryConverter;
    }

    @Override
    public Task convert(CreateTaskRequest source) {
        Task task = new Task();
        task.setId(source.getId());
        task.setDescription(source.getDescription());
        task.setRequires(source.getRequires());

        if (source.getLimits() != null) {
            task.setLimits(limitDefToLimitConverter.convert(source.getLimits()));
        }

        if (source.getCache() != null) {
            task.setCache(cacheDefToCacheConverter.convert(source.getCache()));
        }

        if (source.getRetry() != null) {
            task.setRetry(retryDefToRetryConverter.convert(source.getRetry()));
        }

        task.setEndpoint(source.getEndpoint());
        task.setOutput(outputDefToOutputConverter.convert(source.getOutput()));
        return task;
    }
}
