package com.anurag.darkarmy.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document("workflow_mapping")
public class WorkflowMapping implements Serializable {
    private static final long serialVersionUID = 9114090878049938670L;

    @Id
    private WorkflowMappingID key;

    private String workflow;

    private Date created;

    public WorkflowMappingID getKey() {
        return key;
    }

    public void setKey(WorkflowMappingID key) {
        this.key = key;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
