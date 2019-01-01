package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.common.OutputDef;
import com.anurag.darkarmy.persistence.entity.Output;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OutputDefToOutputConverter implements Converter<OutputDef, Output> {


    @Override
    public Output convert(OutputDef source) {
        Output output = new Output();
        output.setOn(source.getOn());
        output.setCases(source.getCases());
        return output;
    }
}
