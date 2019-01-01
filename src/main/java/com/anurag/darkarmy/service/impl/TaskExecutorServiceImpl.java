package com.anurag.darkarmy.service.impl;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.common.util.AggregationUtil;
import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.EndpointDef;
import com.anurag.darkarmy.core.domain.TaskDef;
import com.anurag.darkarmy.service.DefinitionService;
import com.anurag.darkarmy.service.EndpointExecutorFactory;
import com.anurag.darkarmy.service.EndpointExecutorService;
import com.anurag.darkarmy.service.TaskExecutorService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class TaskExecutorServiceImpl implements TaskExecutorService {
    private final DefinitionService definitionService;
    private final EndpointExecutorFactory endpointExecutorFactory;

    public TaskExecutorServiceImpl(DefinitionService service, EndpointExecutorFactory factory) {
        this.definitionService = service;
        this.endpointExecutorFactory = factory;
    }

    @Override
    public Map execute(TaskDef task, AggregationContext context) {
        Optional<EndpointDef> maybeEndpoint = definitionService.fetchEndpoint(task.getEndpoint());
        if (!maybeEndpoint.isPresent()) {
            throw new SystemException("No endpoint found with ID: '" + task.getEndpoint() + "'");
        }

        EndpointDef endpoint = maybeEndpoint.get();
        Optional<EndpointExecutorService> maybeExecutor = endpointExecutorFactory.getInstance(endpoint);
        if (!maybeExecutor.isPresent()) {
            throw new SystemException("No executor found for endpoint type '" + endpoint.getType() + "'");
        }

        Map endpointResponse = maybeExecutor.get().execute(endpoint, context);
        return AggregationUtil.buildOutput(task.getOutput(), endpointResponse);
    }
}
