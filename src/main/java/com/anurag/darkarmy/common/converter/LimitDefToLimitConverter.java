package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.LimitsDef;
import com.anurag.darkarmy.persistence.entity.Limit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LimitDefToLimitConverter implements Converter<LimitsDef, Limit> {
    @Override
    public Limit convert(LimitsDef source) {
        Limit limit = new Limit();
        limit.setTime(source.getTime());
        limit.setMemory(source.getMemory());
        return limit;
    }
}
