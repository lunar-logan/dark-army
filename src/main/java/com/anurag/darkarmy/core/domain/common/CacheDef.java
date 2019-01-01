package com.anurag.darkarmy.core.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CacheDef implements Serializable {
    private static final long serialVersionUID = -6866708184753947823L;

    public enum Scope {GLOBAL, INSTANCE}

    private boolean enabled;
    private Scope scope;

    // In milliseconds
    private long ttl;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    @Override
    public String toString() {
        return "CacheDef{" +
                "enabled=" + enabled +
                ", scope=" + scope +
                ", ttl='" + ttl + '\'' +
                '}';
    }
}
