package com.anurag.darkarmy.common.exception;

import org.springframework.http.HttpStatus;

public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 7554873061576495168L;
    private final HttpStatus status;
    private final Object resource;

    public SystemException() {
        this(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public SystemException(HttpStatus status) {
        this.status = status;
        this.resource = null;
    }

    public SystemException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.resource = null;
    }

    public SystemException(HttpStatus status, Object resource) {
        this.status = status;
        this.resource = resource;
    }

    public SystemException(String message, HttpStatus status, Object resource) {
        super(message);
        this.status = status;
        this.resource = resource;
    }

    public SystemException(String message, Throwable cause, HttpStatus status, Object resource) {
        super(message, cause);
        this.status = status;
        this.resource = resource;
    }

    public SystemException(Throwable cause, HttpStatus status, Object resource) {
        super(cause);
        this.status = status;
        this.resource = resource;
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status, Object resource) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.resource = resource;
    }

    public SystemException(Throwable cause) {
        super(cause);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.resource = null;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Object getResource() {
        return resource;
    }
}
