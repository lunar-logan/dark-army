package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.WorkflowMappingDef;
import com.anurag.darkarmy.persistence.entity.WorkflowMapping;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WorkflowMappingToWorkflowMappingDefConverter implements Converter<WorkflowMapping, WorkflowMappingDef> {

    @Override
    public WorkflowMappingDef convert(WorkflowMapping source) {
        WorkflowMappingDef def = new WorkflowMappingDef();
        def.setMethod(source.getKey().getMethod());
        def.setPath(source.getKey().getPath());
        def.setWorkflow(source.getWorkflow());
        return def;
    }
}
