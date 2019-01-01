package com.anurag.darkarmy.core.domain;

import com.anurag.darkarmy.core.domain.common.InputDef;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EndpointDef implements Serializable {
    private static final long serialVersionUID = 1999910993373614926L;
    private String id;
    private String uri;
    private String description;
    private String type;
    private InputDef input;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "EndpointDef{" +
                "id='" + id + '\'' +
                ", uri='" + uri + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", input=" + input +
                '}';
    }
}
