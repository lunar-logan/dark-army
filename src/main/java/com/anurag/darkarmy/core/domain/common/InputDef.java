package com.anurag.darkarmy.core.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputDef implements Serializable {
    private static final long serialVersionUID = 6354516439178509013L;

    public enum Method {GET, HEAD, PUT, POST, PATCH, DELETE, OPTIONS}

    private Method method;
    private Map<String, String> path;
    private Map<String, List<String>> query;
    private Map<String, String> header;
    private Map<String, Object> body;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
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

    @Override
    public String toString() {
        return "InputDef{" +
                "method=" + method +
                ", path='" + path + '\'' +
                ", query=" + query +
                ", header=" + header +
                ", body=" + body +
                '}';
    }
}
