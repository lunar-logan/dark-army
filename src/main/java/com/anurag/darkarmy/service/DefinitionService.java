package com.anurag.darkarmy.service;

import com.anurag.darkarmy.core.domain.EndpointDef;
import com.anurag.darkarmy.core.domain.TaskDef;
import com.anurag.darkarmy.core.domain.WorkflowDef;
import com.anurag.darkarmy.core.domain.WorkflowMappingDef;
import com.anurag.darkarmy.core.request.CreateEndpointRequest;
import com.anurag.darkarmy.core.request.CreateTaskRequest;
import com.anurag.darkarmy.core.request.CreateWorkflowMappingRequest;
import com.anurag.darkarmy.core.request.CreateWorkflowRequest;

import java.util.Optional;

public interface DefinitionService {
    Optional<EndpointDef> fetchEndpoint(String id);

    EndpointDef createEndpoint(CreateEndpointRequest request);

    WorkflowDef createWorkflow(CreateWorkflowRequest request);

    Optional<WorkflowDef> fetchWorkflow(String name, int version);

    Optional<WorkflowDef> deleteWorkflow(String name, int version);

    TaskDef createTask(CreateTaskRequest request);

    Optional<TaskDef> fetchTask(String id);

    Optional<TaskDef> deleteTask(String id);

    WorkflowMappingDef createWorkflowMapping(CreateWorkflowMappingRequest request);

    Optional<WorkflowMappingDef> fetchWorkflowMapping(String method, String path);

    Optional<WorkflowMappingDef> deleteWorkflowMapping(String method, String path);
}
