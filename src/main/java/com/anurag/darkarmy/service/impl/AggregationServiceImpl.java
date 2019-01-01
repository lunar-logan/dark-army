package com.anurag.darkarmy.service.impl;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.WorkflowMappingDef;
import com.anurag.darkarmy.core.response.AggregationResponse;
import com.anurag.darkarmy.service.AggregationService;
import com.anurag.darkarmy.service.DefinitionService;
import com.anurag.darkarmy.service.WorkflowExecutorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class AggregationServiceImpl implements AggregationService {
    private final DefinitionService definitionService;
    private final WorkflowExecutorService executorService;

    public AggregationServiceImpl(DefinitionService service, WorkflowExecutorService executorService) {
        this.definitionService = service;
        this.executorService = executorService;
    }

    @Override
    public AggregationResponse aggregate(String method,
                                         String path,
                                         AggregationContext context) {
        Optional<WorkflowMappingDef> maybeWorkflow = definitionService.fetchWorkflowMapping(method, path);
        if (!maybeWorkflow.isPresent()) {
            throw new SystemException(HttpStatus.NOT_FOUND);
        }

        WorkflowMappingDef mapping = maybeWorkflow.get();
        Map workflowResponse = executorService.execute(mapping.getWorkflow(), context);
        return new AggregationResponse(mapping.getWorkflow(), 1, context.getCorrelationId(),
                (int) workflowResponse.getOrDefault("status", 500),
                (Map) workflowResponse.getOrDefault("header", Collections.emptyMap()),
                workflowResponse.get("body"));
    }
}
