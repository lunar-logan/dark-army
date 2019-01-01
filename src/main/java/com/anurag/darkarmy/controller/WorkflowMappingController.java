package com.anurag.darkarmy.controller;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.common.util.ControllerUtil;
import com.anurag.darkarmy.core.request.CreateWorkflowMappingRequest;
import com.anurag.darkarmy.core.response.OperationResponse;
import com.anurag.darkarmy.service.DefinitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workflowMapping")
public class WorkflowMappingController {
    private final DefinitionService service;

    public WorkflowMappingController(DefinitionService service) {
        this.service = service;
    }

    @PutMapping("/")
    public ResponseEntity<OperationResponse> create(@RequestBody CreateWorkflowMappingRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new OperationResponse(service.createWorkflowMapping(request)));
        } catch (SystemException ex) {
            return ResponseEntity.status(ex.getStatus()).body(new OperationResponse(ex.getResource()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<OperationResponse> fetch(@RequestParam("method") String method,
                                                   @RequestParam("path") String path) {
        return ControllerUtil.asResponseEntity(service.fetchWorkflowMapping(method, path).orElse(null));
    }

    @DeleteMapping("/")
    public ResponseEntity<OperationResponse> delete(@RequestParam("method") String method,
                                                    @RequestParam("path") String path) {
        return ControllerUtil.asResponseEntity(service.deleteWorkflowMapping(method, path).orElse(null));
    }
}
