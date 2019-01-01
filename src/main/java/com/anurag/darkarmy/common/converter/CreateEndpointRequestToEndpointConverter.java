package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.request.CreateEndpointRequest;
import com.anurag.darkarmy.persistence.entity.Endpoint;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateEndpointRequestToEndpointConverter implements Converter<CreateEndpointRequest, Endpoint> {

    private final InputDefToInputConverter inputDefToInputConverter;

    public CreateEndpointRequestToEndpointConverter(InputDefToInputConverter inputDefToInputConverter) {
        this.inputDefToInputConverter = inputDefToInputConverter;
    }

    @Override
    public Endpoint convert(CreateEndpointRequest request) {
        Endpoint ep = new Endpoint();
        ep.setId(request.getId());
        ep.setDescription(request.getDescription());
        ep.setUri(request.getUri());
        ep.setInput(inputDefToInputConverter.convert(request.getInput()));
        ep.setType(request.getType());
        return ep;
    }
}
