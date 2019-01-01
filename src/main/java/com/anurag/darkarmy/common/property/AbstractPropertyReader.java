package com.anurag.darkarmy.common.property;

import java.util.Optional;

public abstract class AbstractPropertyReader implements PropertyReader {
    protected final PropertyReader next;

    public AbstractPropertyReader(PropertyReader next) {
        this.next = next;
    }

    @Override
    public Optional<String> read(Property prop) {
        if (next != null) {
            return next.read(prop);
        }
        return Optional.empty();
    }
}
