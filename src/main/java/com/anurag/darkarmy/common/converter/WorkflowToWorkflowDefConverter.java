package com.anurag.darkarmy.common.converter;

import com.anurag.darkarmy.core.domain.WorkflowDef;
import com.anurag.darkarmy.persistence.entity.Workflow;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WorkflowToWorkflowDefConverter implements Converter<Workflow, WorkflowDef> {
    private final LimitToLimitDefConverter limitToLimitDefConverter;
    private final OutputToOutputDefConverter outputToOutputDefConverter;

    public WorkflowToWorkflowDefConverter(LimitToLimitDefConverter limitToLimitDefConverter, OutputToOutputDefConverter outputToOutputDefConverter) {
        this.limitToLimitDefConverter = limitToLimitDefConverter;
        this.outputToOutputDefConverter = outputToOutputDefConverter;
    }

    @Override
    public WorkflowDef convert(Workflow source) {
        WorkflowDef def = new WorkflowDef();
        def.setId(source.getId().getName());
        def.setVersion(source.getId().getVersion());
        def.setDescription(source.getDescription());
        def.setTasks(source.getTasks());
        if(source.getLimits()  != null) {
            def.setLimit(limitToLimitDefConverter.convert(source.getLimits()));
        }

        def.setOutput(outputToOutputDefConverter.convert(source.getOutput()));
        return def;
    }
}
