package com.anurag.darkarmy.core.domain;

import com.anurag.darkarmy.core.domain.common.LimitsDef;
import com.anurag.darkarmy.core.domain.common.OutputDef;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkflowDef implements Serializable {
    private static final long serialVersionUID = -7576350370270475931L;
    private String id;
    private int version;
    private String description;
    private List<String> tasks;
    private LimitsDef limit;
    private OutputDef output;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public LimitsDef getLimit() {
        return limit;
    }

    public void setLimit(LimitsDef limit) {
        this.limit = limit;
    }

    public OutputDef getOutput() {
        return output;
    }

    public void setOutput(OutputDef output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "WorkflowDef{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", limit=" + limit +
                ", output=" + output +
                '}';
    }
}
