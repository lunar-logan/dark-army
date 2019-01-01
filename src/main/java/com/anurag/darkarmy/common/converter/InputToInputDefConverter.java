package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.InputDef;
import com.anurag.darkarmy.persistence.entity.Input;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InputToInputDefConverter implements Converter<Input, InputDef> {
    @Override
    public InputDef convert(Input source) {
        InputDef def = new InputDef();
        def.setMethod(InputDef.Method.valueOf(source.getMethod()));
        def.setPath(source.getPath());
        def.setQuery(source.getQuery());
        def.setHeader(source.getHeader());
        def.setBody(source.getBody());
        return def;
    }
}
