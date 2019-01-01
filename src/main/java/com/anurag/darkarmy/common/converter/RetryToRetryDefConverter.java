package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.RetryDef;
import com.anurag.darkarmy.persistence.entity.Retry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RetryToRetryDefConverter implements Converter<Retry, RetryDef> {

    @Override
    public RetryDef convert(Retry source) {
        RetryDef def = new RetryDef();
        def.setOn(source.getOn());
        def.setBackoff(RetryDef.BackOff.valueOf(source.getBackoff()));
        def.setDelay(source.getDelay());
        def.setMaxAttempts(source.getMaxAttempts());
        def.setReload(source.getReload());
        return def;
    }
}
