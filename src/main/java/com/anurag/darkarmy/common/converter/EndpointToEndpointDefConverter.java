package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.EndpointDef;
import com.anurag.darkarmy.persistence.entity.Endpoint;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EndpointToEndpointDefConverter implements Converter<Endpoint, EndpointDef> {

    private final InputToInputDefConverter inputToInputDefConverter;

    public EndpointToEndpointDefConverter(InputToInputDefConverter inputToInputDefConverter) {
        this.inputToInputDefConverter = inputToInputDefConverter;
    }

    @Override
    public EndpointDef convert(Endpoint source) {
        EndpointDef def = new EndpointDef();
        def.setId(source.getId());
        def.setDescription(source.getDescription());
        def.setType(source.getType());
        def.setUri(source.getUri());
        def.setInput(inputToInputDefConverter.convert(source.getInput()));
        return def;
    }
}
