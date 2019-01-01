package com.anurag.darkarmy.controller;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.common.util.ControllerUtil;
import com.anurag.darkarmy.core.request.CreateTaskRequest;
import com.anurag.darkarmy.core.response.OperationResponse;
import com.anurag.darkarmy.service.DefinitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final DefinitionService service;

    public TaskController(DefinitionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResponse> fetch(@PathVariable("id") String taskId) {
        return ControllerUtil.asResponseEntity(service.fetchTask(taskId).orElse(null));
    }

    @PutMapping("/")
    public ResponseEntity<OperationResponse> create(@RequestBody @Validated CreateTaskRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new OperationResponse(service.createTask(request)));
        } catch (SystemException ex) {
            return ResponseEntity.status(ex.getStatus()).body(new OperationResponse(ex.getResource()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResponse> delete(@PathVariable("id") String taskId) {
        return ControllerUtil.asResponseEntity(service.deleteTask(taskId).orElse(null));
    }
}
