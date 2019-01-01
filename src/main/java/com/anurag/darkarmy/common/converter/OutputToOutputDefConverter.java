package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.OutputDef;
import com.anurag.darkarmy.persistence.entity.Output;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OutputToOutputDefConverter implements Converter<Output, OutputDef> {

    @Override
    public OutputDef convert(Output source) {
        OutputDef def = new OutputDef();
        def.setOn(source.getOn());
        def.setCases(source.getCases());
        return def;
    }
}
