package com.anurag.darkarmy.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AggregationResponse implements Serializable {
    private static final long serialVersionUID = -2981310249684588416L;
    private final String name;
    private final int version;
    private final String correlationId;
    private final int status;
    private final Map<String, String> header;
    private final Object body;

    public AggregationResponse(String name, int version, String correlationId, int status, Map<String, String> header, Object body) {
        this.name = name;
        this.version = version;
        this.correlationId = correlationId;
        this.status = status;
        this.header = Collections.unmodifiableMap(header);
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "AggregationResponse{" +
                "name='" + name + '\'' +
                ", version=" + version +
                ", correlationId='" + correlationId + '\'' +
                ", status=" + status +
                ", header=" + header +
                ", body=" + body +
                '}';
    }
}
