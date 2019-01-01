package com.anurag.darkarmy.service.impl;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.core.domain.EndpointDef;
import com.anurag.darkarmy.core.domain.TaskDef;
import com.anurag.darkarmy.core.domain.WorkflowDef;
import com.anurag.darkarmy.core.domain.WorkflowMappingDef;
import com.anurag.darkarmy.core.request.CreateEndpointRequest;
import com.anurag.darkarmy.core.request.CreateTaskRequest;
import com.anurag.darkarmy.core.request.CreateWorkflowMappingRequest;
import com.anurag.darkarmy.core.request.CreateWorkflowRequest;
import com.anurag.darkarmy.persistence.dao.EndpointDAO;
import com.anurag.darkarmy.persistence.dao.TaskDAO;
import com.anurag.darkarmy.persistence.dao.WorkflowDAO;
import com.anurag.darkarmy.persistence.dao.WorkflowMappingDAO;
import com.anurag.darkarmy.persistence.entity.*;
import com.anurag.darkarmy.service.DefinitionService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefinitionServiceImpl implements DefinitionService {
    private final WorkflowDAO workflowDAO;
    private final EndpointDAO endpointDAO;
    private final TaskDAO taskDAO;
    private final WorkflowMappingDAO workflowMappingDAO;
    private final ConversionService conversionService;

    public DefinitionServiceImpl(WorkflowDAO workflowDAO, EndpointDAO endpointDAO, TaskDAO taskDAO, WorkflowMappingDAO workflowMappingDAO, ConversionService conversionService) {
        this.workflowDAO = workflowDAO;
        this.endpointDAO = endpointDAO;
        this.taskDAO = taskDAO;
        this.workflowMappingDAO = workflowMappingDAO;
        this.conversionService = conversionService;
    }

    @Override
    public Optional<EndpointDef> fetchEndpoint(String id) {
        return endpointDAO.findById(id).map(ep -> conversionService.convert(ep, EndpointDef.class));
    }

    @Override
    public EndpointDef createEndpoint(CreateEndpointRequest request) {
        Optional<Endpoint> maybe = endpointDAO.findById(request.getId());
        if (maybe.isPresent()) {
            throw new SystemException(HttpStatus.FOUND, maybe.get());
        }
        Endpoint entity = conversionService.convert(request, Endpoint.class);
        endpointDAO.save(entity);
        return conversionService.convert(entity, EndpointDef.class);
    }

    @Override
    public WorkflowDef createWorkflow(CreateWorkflowRequest request) {
        Optional<WorkflowDef> maybe = fetchWorkflow(request.getId(), request.getVersion());
        if (maybe.isPresent()) {
            throw new SystemException(HttpStatus.FOUND, maybe.get());
        }
        Workflow entity = conversionService.convert(request, Workflow.class);
        workflowDAO.save(entity);
        return conversionService.convert(entity, WorkflowDef.class);
    }

    @Override
    public Optional<WorkflowDef> fetchWorkflow(String name, int version) {
        return workflowDAO.findById(WorkflowID.valueOf(name, version))
                .map(entity -> conversionService.convert(entity, WorkflowDef.class));
    }

    @Override
    public Optional<WorkflowDef> deleteWorkflow(String name, int version) {
        WorkflowID id = WorkflowID.valueOf(name, version);
        Optional<Workflow> maybe = workflowDAO.findById(id);
        if (maybe.isPresent()) {
            workflowDAO.deleteById(id);
        }
        return maybe.map(entity -> conversionService.convert(entity, WorkflowDef.class));
    }

    @Override
    public TaskDef createTask(CreateTaskRequest request) {
        Optional<Task> maybe = taskDAO.findById(request.getId());
        if (maybe.isPresent()) {
            throw new SystemException(HttpStatus.FOUND, maybe.get());
        }
        Task entity = conversionService.convert(request, Task.class);
        taskDAO.save(entity);
        return conversionService.convert(entity, TaskDef.class);
    }

    @Override
    public Optional<TaskDef> fetchTask(String id) {
        return taskDAO.findById(id).map(entity -> conversionService.convert(entity, TaskDef.class));
    }

    @Override
    public Optional<TaskDef> deleteTask(String id) {
        Optional<Task> maybe = taskDAO.findById(id);
        if (maybe.isPresent()) {
            taskDAO.deleteById(id);
        }
        return maybe.map(entity -> conversionService.convert(entity, TaskDef.class));
    }

    @Override
    public WorkflowMappingDef createWorkflowMapping(CreateWorkflowMappingRequest request) {
        WorkflowMappingID id = WorkflowMappingID.valueOf(request.getMethod(), request.getPath());
        Optional<WorkflowMapping> maybeMapping = workflowMappingDAO.findById(id);
        if (maybeMapping.isPresent()) {
            throw new SystemException(HttpStatus.FOUND, maybeMapping.get());
        }

        WorkflowMapping mapping = conversionService.convert(request, WorkflowMapping.class);
        workflowMappingDAO.save(mapping);

        return conversionService.convert(mapping, WorkflowMappingDef.class);
    }

    @Override
    public Optional<WorkflowMappingDef> fetchWorkflowMapping(String method, String path) {
        return workflowMappingDAO.findById(WorkflowMappingID.valueOf(method, path))
                .map(entity -> conversionService.convert(entity, WorkflowMappingDef.class));
    }

    @Override
    public Optional<WorkflowMappingDef> deleteWorkflowMapping(String method, String path) {
        WorkflowMappingID id = WorkflowMappingID.valueOf(method, path);
        Optional<WorkflowMapping> maybe = workflowMappingDAO.findById(id);
        if (maybe.isPresent()) {
            workflowMappingDAO.deleteById(id);
        }
        return maybe.map(entity -> conversionService.convert(entity, WorkflowMappingDef.class));
    }

}
