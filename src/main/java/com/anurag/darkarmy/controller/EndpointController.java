package com.anurag.darkarmy.controller;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.common.util.ControllerUtil;
import com.anurag.darkarmy.core.request.CreateEndpointRequest;
import com.anurag.darkarmy.core.response.OperationResponse;
import com.anurag.darkarmy.service.DefinitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endpoint")
public class EndpointController {
    private final DefinitionService service;

    public EndpointController(DefinitionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResponse> fetch(@PathVariable("id") String endpointId) {
        return ControllerUtil.asResponseEntity(service.fetchEndpoint(endpointId).orElse(null));
    }

    @PutMapping("/")
    public ResponseEntity<OperationResponse> create(@RequestBody CreateEndpointRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new OperationResponse(service.createEndpoint(request)));
        } catch (SystemException ex) {
            return ResponseEntity.status(ex.getStatus()).body(new OperationResponse(ex.getResource()));
        }
    }
}