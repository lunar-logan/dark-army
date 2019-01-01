package com.anurag.darkarmy.persistence.entity;

import java.io.Serializable;
import java.util.Map;

public class Output implements Serializable {
    private static final long serialVersionUID = 8278836215104828434L;

    private String on;
    private Map<String, Map<String, Object>> cases;

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public Map<String, Map<String, Object>> getCases() {
        return cases;
    }

    public void setCases(Map<String, Map<String, Object>> cases) {
        this.cases = cases;
    }
}
