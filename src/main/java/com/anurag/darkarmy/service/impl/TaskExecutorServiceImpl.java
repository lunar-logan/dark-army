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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Map;

@Service
public class TaskExecutorServiceImpl implements TaskExecutorService {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final DefinitionService definitionService;
    private final EndpointExecutorFactory endpointExecutorFactory;

    public TaskExecutorServiceImpl(DefinitionService service, EndpointExecutorFactory factory) {
        this.definitionService = service;
        this.endpointExecutorFactory = factory;
    }

    @Override
    public Map execute(TaskDef task, AggregationContext context) {
        EndpointDef endpoint = definitionService.fetchEndpoint(task.getEndpoint()).orElseThrow(() -> new SystemException(HttpStatus.BAD_REQUEST, "No endpoint found with ID: '" + task.getEndpoint() + "'"));
        log.trace("Endpoint definition: {}", endpoint);

        EndpointExecutorService executor = endpointExecutorFactory.getInstance(endpoint).orElseThrow(() -> new SystemException(HttpStatus.BAD_REQUEST, "No executor for endpoint type: '" + endpoint.getType() + "'"));

        Map endpointResponse = executor.execute(endpoint, context);
        log.trace("Endpoint ID: '{}', Response: {}", endpoint.getId(), endpointResponse);

        return AggregationUtil.buildOutput(task.getOutput(), endpointResponse);
    }
}
