package com.anurag.darkarmy.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowMappingDef implements Serializable {
    private static final long serialVersionUID = 1888834002087105873L;

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
        return "WorkflowMappingDef{" +
                "path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", workflow='" + workflow + '\'' +
                '}';
    }
}
