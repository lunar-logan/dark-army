package com.anurag.darkarmy.core.request;

import com.anurag.darkarmy.core.domain.common.CacheDef;
import com.anurag.darkarmy.core.domain.common.LimitsDef;
import com.anurag.darkarmy.core.domain.common.OutputDef;
import com.anurag.darkarmy.core.domain.common.RetryDef;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskRequest implements Serializable {
    private static final long serialVersionUID = -757028894109302211L;

    @NotNull
    private String id;

    private String description;

    private List<String> requires;

    private CacheDef cache;

    private LimitsDef limits;

    private RetryDef retry;

    @NotNull
    private String endpoint;

    @NotNull
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
        return "CreateTaskRequest{" +
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
