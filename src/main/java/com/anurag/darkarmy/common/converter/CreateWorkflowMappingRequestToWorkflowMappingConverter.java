package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.request.CreateWorkflowMappingRequest;
import com.anurag.darkarmy.persistence.entity.WorkflowMapping;
import com.anurag.darkarmy.persistence.entity.WorkflowMappingID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateWorkflowMappingRequestToWorkflowMappingConverter implements Converter<CreateWorkflowMappingRequest, WorkflowMapping> {


    @Override
    public WorkflowMapping convert(CreateWorkflowMappingRequest source) {
        WorkflowMapping mapping = new WorkflowMapping();
        mapping.setKey(buildWorkflowMappingID(source));
        mapping.setWorkflow(source.getWorkflow());
        mapping.setCreated(new Date());
        return mapping;
    }

    private WorkflowMappingID buildWorkflowMappingID(CreateWorkflowMappingRequest request) {
        WorkflowMappingID id = new WorkflowMappingID();
        id.setPath(request.getPath());
        id.setMethod(request.getMethod());
        return id;
    }
}
