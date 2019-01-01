package com.anurag.darkarmy.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationResponse implements Serializable {
    private static final long serialVersionUID = 6251442510752614L;
    protected Object data;

    public OperationResponse(Object data) {
        this.data = data;
    }

    public OperationResponse() {
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OperationResponse{" +
                "data=" + data +
                '}';
    }
}
