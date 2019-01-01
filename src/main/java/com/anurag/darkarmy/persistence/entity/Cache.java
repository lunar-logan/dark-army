package com.anurag.darkarmy.persistence.entity;

import java.io.Serializable;

public class Cache implements Serializable {
    private static final long serialVersionUID = -6058856131385179159L;

    private boolean enabled;
    private String scope;
    private long ttl;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
