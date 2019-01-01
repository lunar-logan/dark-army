package com.anurag.darkarmy.core.request;

import com.anurag.darkarmy.core.domain.common.InputDef;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class CreateEndpointRequest implements Serializable {
    private static final long serialVersionUID = -1357610468151887392L;
    private String id;
    private String description;
    private String uri;
    private String type;
    private InputDef input;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InputDef getInput() {
        return input;
    }

    public void setInput(InputDef input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "CreateEndpointRequest{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", uri='" + uri + '\'' +
                ", type='" + type + '\'' +
                ", input=" + input +
                '}';
    }

    public static void main(String[] args) throws Exception{
        ObjectMapper m = new ObjectMapper();
        System.out.println(m.writeValueAsString(new CreateEndpointRequest() {{
            setInput(new InputDef());
        }}));
    }
}
