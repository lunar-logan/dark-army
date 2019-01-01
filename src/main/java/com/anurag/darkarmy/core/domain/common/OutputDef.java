package com.anurag.darkarmy.core.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputDef implements Serializable {
    private static final long serialVersionUID = 52552589112926782L;
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

    @Override
    public String toString() {
        return "OutputDef{" +
                "on='" + on + '\'' +
                ", cases=" + cases +
                '}';
    }
}
