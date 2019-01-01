package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.EndpointDef;
import com.anurag.darkarmy.persistence.entity.Endpoint;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EndpointDefToEndpointConverter implements Converter<EndpointDef, Endpoint> {
    private final InputDefToInputConverter inputDefToInputConverter;

    public EndpointDefToEndpointConverter(InputDefToInputConverter inputDefToInputConverter) {
        this.inputDefToInputConverter = inputDefToInputConverter;
    }

    @Override
    public Endpoint convert(EndpointDef source) {
        Endpoint endpoint = new Endpoint();
        endpoint.setUri(source.getUri());
        endpoint.setType(source.getType());
        endpoint.setInput(inputDefToInputConverter.convert(source.getInput()));
        endpoint.setId(source.getId());
        endpoint.setDescription(source.getDescription());
        return endpoint;
    }
}
