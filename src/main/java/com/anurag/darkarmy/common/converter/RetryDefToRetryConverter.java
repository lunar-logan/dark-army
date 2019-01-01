package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.RetryDef;
import com.anurag.darkarmy.persistence.entity.Retry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RetryDefToRetryConverter implements Converter<RetryDef, Retry> {
    @Override
    public Retry convert(RetryDef source) {
        Retry retry = new Retry();
        retry.setOn(source.getOn());
        retry.setReload(source.getReload());
        retry.setBackoff(source.getBackoff().name());
        retry.setDelay(source.getDelay());
        retry.setMaxAttempts(source.getMaxAttempts());
        return retry;
    }
}
