package com.anurag.darkarmy.core.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetryDef implements Serializable {
    private static final long serialVersionUID = -5962312229014948168L;

    public enum BackOff {FIXED, EXPONENTIAL}

    private String on;
    private List<String> reload;
    private long delay;
    private BackOff backoff;
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

    public BackOff getBackoff() {
        return backoff;
    }

    public void setBackoff(BackOff backoff) {
        this.backoff = backoff;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public String toString() {
        return "RetryDef{" +
                "on='" + on + '\'' +
                ", reload=" + reload +
                ", delay='" + delay + '\'' +
                ", backoff=" + backoff +
                ", maxAttempts=" + maxAttempts +
                '}';
    }
}
