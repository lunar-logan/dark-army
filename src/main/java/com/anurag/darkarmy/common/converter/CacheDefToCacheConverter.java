package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.CacheDef;
import com.anurag.darkarmy.persistence.entity.Cache;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CacheDefToCacheConverter implements Converter<CacheDef, Cache> {


    @Override
    public Cache convert(CacheDef source) {
        Cache cache = new Cache();
        cache.setEnabled(source.isEnabled());
        cache.setScope(source.getScope().name());
        cache.setTtl(source.getTtl());
        return cache;
    }
}
