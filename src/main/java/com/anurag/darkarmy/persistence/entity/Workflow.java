package com.anurag.darkarmy.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;


@Document("workflow")
public class Workflow implements Serializable {
    private static final long serialVersionUID = -3279726798589750113L;

    @Id
    private WorkflowID id;

    private String description;

    private List<String> tasks;

    private Limit limits;

    private Output output;

    public WorkflowID getId() {
        return id;
    }

    public void setId(WorkflowID id) {
        this.id = id;
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

    public Limit getLimits() {
        return limits;
    }

    public void setLimits(Limit limits) {
        this.limits = limits;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }
}
