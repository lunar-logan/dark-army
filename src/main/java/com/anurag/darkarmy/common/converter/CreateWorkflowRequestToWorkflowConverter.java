package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.common.util.DurationParser;
import com.anurag.darkarmy.core.request.CreateWorkflowRequest;
import com.anurag.darkarmy.persistence.entity.Limit;
import com.anurag.darkarmy.persistence.entity.Workflow;
import com.anurag.darkarmy.persistence.entity.WorkflowID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CreateWorkflowRequestToWorkflowConverter implements Converter<CreateWorkflowRequest, Workflow> {

    private final OutputDefToOutputConverter outputDefToOutputConverter;

    public CreateWorkflowRequestToWorkflowConverter(OutputDefToOutputConverter outputDefToOutputConverter) {
        this.outputDefToOutputConverter = outputDefToOutputConverter;
    }

    @Override
    public Workflow convert(CreateWorkflowRequest request) {
        Workflow workflow = new Workflow();
        workflow.setId(buildWorkflowID(request));
        workflow.setDescription(request.getDescription());
        workflow.setTasks(request.getTasks());
        workflow.setLimits(buildLimit(request));
        workflow.setOutput(outputDefToOutputConverter.convert(request.getOutput()));
        return workflow;
    }

    private WorkflowID buildWorkflowID(CreateWorkflowRequest request) {
        WorkflowID id = new WorkflowID();
        id.setName(request.getId());
        id.setVersion(request.getVersion());
        return id;
    }

    private Limit buildLimit(CreateWorkflowRequest request) {
        Limit limit = new Limit();
        limit.setTime(DurationParser.getSimpleInstance().parse(request.getTimeout(), TimeUnit.MILLISECONDS));
        limit.setMemory(1024 * 1024);
        return limit;
    }
}
