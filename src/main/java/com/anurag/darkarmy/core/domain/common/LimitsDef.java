package com.anurag.darkarmy.core.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LimitsDef implements Serializable {
    private static final long serialVersionUID = -712764955224919100L;

    // in milliseconds
    private long time;

    // in bytes
    private long memory;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getMemory() {
        return memory;
    }

    public void setMemory(long memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "LimitsDef{" +
                "time='" + time + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
