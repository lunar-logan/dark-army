package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.CacheDef;
import com.anurag.darkarmy.persistence.entity.Cache;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CacheToCacheDefConverter implements Converter<Cache, CacheDef> {
    @Override
    public CacheDef convert(Cache source) {
        CacheDef def = new CacheDef();
        def.setEnabled(source.isEnabled());
        def.setScope(CacheDef.Scope.valueOf(source.getScope()));
        def.setTtl(source.getTtl());
        return def;
    }
}
