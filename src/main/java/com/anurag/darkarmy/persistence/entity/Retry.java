package com.anurag.darkarmy.persistence.entity;

import java.io.Serializable;
import java.util.List;

public class Retry implements Serializable {
    private static final long serialVersionUID = -8818439253264852752L;

    private String on;
    private List<String> reload;
    private long delay;
    private String backoff;
    private int maxAttempts;

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public List<String> getReload() {
        return reload;
    }

    public void setReload(List<String> reload) {
        this.reload = reload;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public String getBackoff() {
        return backoff;
    }

    public void setBackoff(String backoff) {
        this.backoff = backoff;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
