package com.anurag.darkarmy.common.property;

public enum Property {
    ROOT_CONTEXT_NAME("root.context.name", "root");

    private final String key;
    private final String value;

    Property(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
