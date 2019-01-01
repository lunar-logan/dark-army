package com.anurag.darkarmy.controller;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.common.util.ControllerUtil;
import com.anurag.darkarmy.core.request.CreateWorkflowRequest;
import com.anurag.darkarmy.core.response.OperationResponse;
import com.anurag.darkarmy.service.DefinitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {
    private final DefinitionService service;

    public WorkflowController(DefinitionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResponse> fetch(@PathVariable("id") String name, @RequestParam("version") int version) {
        return ControllerUtil.asResponseEntity(service.fetchWorkflow(name, version).orElse(null));
    }

    @PutMapping("/")
    public ResponseEntity<OperationResponse> create(@RequestBody @Validated CreateWorkflowRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new OperationResponse(service.createWorkflow(request)));
        } catch (SystemException ex) {
            return ResponseEntity.status(ex.getStatus()).body(new OperationResponse(ex.getResource()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResponse> delete(@PathVariable("id") String name, @RequestParam("version") int version) {
        return ControllerUtil.asResponseEntity(service.deleteWorkflow(name, version).orElse(null));
    }
}
