package com.anurag.darkarmy.core.domain;

import com.anurag.darkarmy.core.domain.common.CacheDef;
import com.anurag.darkarmy.core.domain.common.LimitsDef;
import com.anurag.darkarmy.core.domain.common.OutputDef;
import com.anurag.darkarmy.core.domain.common.RetryDef;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDef implements Serializable {
    private static final long serialVersionUID = 2715718961408848524L;

    private String id;

    private String description;

    private List<String> requires;

    private CacheDef cache;

    private LimitsDef limits;

    private RetryDef retry;

    private String endpoint;

    private OutputDef output;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }

    public CacheDef getCache() {
        return cache;
    }

    public void setCache(CacheDef cache) {
        this.cache = cache;
    }

    public LimitsDef getLimits() {
        return limits;
    }

    public void setLimits(LimitsDef limits) {
        this.limits = limits;
    }

    public RetryDef getRetry() {
        return retry;
    }

    public void setRetry(RetryDef retry) {
        this.retry = retry;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public OutputDef getOutput() {
        return output;
    }

    public void setOutput(OutputDef output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "TaskDef{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", requires=" + requires +
                ", cache=" + cache +
                ", limits=" + limits +
                ", retry=" + retry +
                ", endpoint=" + endpoint +
                ", output=" + output +
                '}';
    }
}
