package com.anurag.darkarmy.common.util;

import com.anurag.darkarmy.core.response.OperationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ControllerUtil {
    private ControllerUtil() {
    }

    public static ResponseEntity<OperationResponse> asResponseEntity(Object maybeData) {
        if (maybeData != null) {
            return ResponseEntity.ok(new OperationResponse(maybeData));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OperationResponse());
    }
}
