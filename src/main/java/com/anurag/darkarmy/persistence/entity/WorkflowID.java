package com.anurag.darkarmy.persistence.entity;

import java.io.Serializable;
import java.util.Objects;


public class WorkflowID implements Serializable {
    private static final long serialVersionUID = 7759676928057787802L;
    private String name;
    private int version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowID that = (WorkflowID) o;
        return version == that.version &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version);
    }

    public static WorkflowID valueOf(String name, int version) {
        WorkflowID id = new WorkflowID();
        id.setName(name);
        id.setVersion(version);
        return id;
    }
}
