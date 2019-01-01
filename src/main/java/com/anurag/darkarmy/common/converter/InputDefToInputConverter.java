package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.InputDef;
import com.anurag.darkarmy.persistence.entity.Input;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InputDefToInputConverter implements Converter<InputDef, Input> {

    @Override
    public Input convert(InputDef source) {
        Input input = new Input();
        input.setMethod(source.getMethod().name());
        input.setPath(source.getPath());
        input.setQuery(source.getQuery());
        input.setHeader(source.getHeader());
        input.setBody(source.getBody());
        return input;
    }
}
