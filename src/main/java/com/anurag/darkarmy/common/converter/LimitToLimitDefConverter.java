package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.LimitsDef;
import com.anurag.darkarmy.persistence.entity.Limit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LimitToLimitDefConverter implements Converter<Limit, LimitsDef> {
    @Override
    public LimitsDef convert(Limit source) {
        LimitsDef def = new LimitsDef();
        def.setTime(source.getTime());
        def.setMemory(source.getMemory());
        return def;
    }
}
