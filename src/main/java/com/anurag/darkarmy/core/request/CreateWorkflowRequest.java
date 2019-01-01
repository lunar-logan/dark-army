package com.anurag.darkarmy.core.request;

import com.anurag.darkarmy.core.domain.common.OutputDef;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWorkflowRequest implements Serializable {
    private static final long serialVersionUID = -5419849813008783105L;

    @NotNull
    private String id;

    @PositiveOrZero
    private int version;
    private String description;

    @NotEmpty
    private List<String> tasks;

    @NotNull
    private String timeout;

    private String memoryLimit;

    @NotNull
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

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public OutputDef getOutput() {
        return output;
    }

    public void setOutput(OutputDef output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "CreateWorkflowRequest{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", timeout='" + timeout + '\'' +
                ", memoryLimit='" + memoryLimit + '\'' +
                ", output=" + output +
                '}';
    }

    public static void main(String[] args)throws Exception {
        ObjectMapper m = new ObjectMapper();
        CreateWorkflowRequest req = new CreateWorkflowRequest();
        req.setOutput(new OutputDef());
        System.out.println(m.writeValueAsString(req));
    }
}
