package com.anurag.darkarmy.core.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWorkflowMappingRequest implements Serializable {
    private static final long serialVersionUID = 3319905742223140144L;

    private String path;
    private String method;
    private String workflow;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    @Override
    public String toString() {
        return "CreateWorkflowMappingRequest{" +
                "path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", workflow='" + workflow + '\'' +
                '}';
    }
}
