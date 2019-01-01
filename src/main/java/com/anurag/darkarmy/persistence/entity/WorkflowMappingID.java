package com.anurag.darkarmy.persistence.entity;

import java.io.Serializable;
import java.util.Objects;


public class WorkflowMappingID implements Serializable {
    private static final long serialVersionUID = -8653249712527925099L;
    private String path;
    private String method;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowMappingID that = (WorkflowMappingID) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method);
    }

    public static WorkflowMappingID valueOf(String method, String path) {
        WorkflowMappingID id = new WorkflowMappingID();
        id.setMethod(method);
        id.setPath(path);
        return id;
    }
}
