package com.anurag.darkarmy.common.property;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public final class PropertyManager {
    private final PropertyReader chain;

    public PropertyManager(@Qualifier("db_reader") PropertyReader chain) {
        this.chain = chain;
    }

    public Optional<String> get(Property prop) {
        return chain.read(prop);
    }

    public Optional<Integer> getInt(Property prop) {
        return get(prop).map(Integer::valueOf);
    }

    public Optional<Long> getLong(Property prop) {
        return get(prop).map(Long::valueOf);
    }

    public Optional<Boolean> getBool(Property prop) {
        return get(prop).map(Boolean::valueOf);
    }

    public Optional<Double> getDouble(Property prop) {
        return get(prop).map(Double::valueOf);
    }

    public Optional<Float> getFloat(Property prop) {
        return get(prop).map(Float::valueOf);
    }

    public Optional<Set<String>> getStrSet(Property property, String delimiter) {
        return get(property).map(v -> v.split(delimiter)).map(arr -> new HashSet<>(Arrays.asList(arr)));
    }
}
