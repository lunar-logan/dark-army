package com.anurag.darkarmy.persistence.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Input implements Serializable {
    private static final long serialVersionUID = 3148467543379931199L;

    private String method;
    private Map<String, String> path;
    private Map<String, List<String>> query;
    private Map<String, String> header;
    private Map<String, Object> body;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getPath() {
        return path;
    }

    public void setPath(Map<String, String> path) {
        this.path = path;
    }

    public Map<String, List<String>> getQuery() {
        return query;
    }

    public void setQuery(Map<String, List<String>> query) {
        this.query = query;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
