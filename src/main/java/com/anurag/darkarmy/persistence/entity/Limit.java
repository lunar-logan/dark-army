package com.anurag.darkarmy.persistence.entity;

import java.io.Serializable;

public class Limit implements Serializable {
    private static final long serialVersionUID = 7882766904807591825L;
    private long time;
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
}
