package com.anurag.darkarmy.core.request;

import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.TaskDef;
import com.anurag.darkarmy.core.domain.WorkflowDef;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregationRequest implements Serializable {
    private static final long serialVersionUID = -6792112162369734366L;

    private WorkflowDef workflow;
    private List<TaskDef> tasks;
    private AggregationContext context;

    public WorkflowDef getWorkflow() {
        return workflow;
    }

    public void setWorkflow(WorkflowDef workflow) {
        this.workflow = workflow;
    }

    public List<TaskDef> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDef> tasks) {
        this.tasks = tasks;
    }

    public AggregationContext getContext() {
        return context;
    }

    public void setContext(AggregationContext context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AggregationRequest{" +
                "workflow=" + workflow +
                ", tasks=" + tasks +
                ", context=" + context +
                '}';
    }
}
