package com.anurag.darkarmy.common.util;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

/**
 * Utility functions related to List
 */
public final class CollectionUtil {

    @SuppressWarnings("unchecked")
    public static <T> T[] explode(List<T> list, Class<T> clazz) {
        T[] array = (T[]) Array.newInstance(clazz, list.size());
        Iterator<T> itr = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            array[i] = itr.next();
        }
        return array;
    }

    public static <E> List<E> getOrEmpty(List<E> value) {
        if (value == null) {
            return Collections.emptyList();
        }
        return value;
    }

    public static <E> Set<E> getOrEmpty(Set<E> value) {
        if (value == null) {
            return Collections.emptySet();
        }
        return value;
    }

    public static <K, V> Map<K, V> getOrEmpty(Map<K, V> value) {
        if (value == null) {
            return Collections.emptyMap();
        }
        return value;
    }

    public static <K, V> Map<K, V> merge(Map<K, V> m1, Map<K, V> m2) {
        Map<K, V> merged = new HashMap<>();
        if (m1 != null) {
            merged.putAll(m1);
        }
        if (m2 != null) {
            merged.putAll(m2);
        }
        return merged;
    }

    public static <E> List<E> join(List<E>... lists) {
        List<E> merged = new ArrayList<>();
        for (List<E> list : lists) {
            merged.addAll(list);
        }
        return merged;
    }

    public static <K, V> Map<K, V> asSingleValueMap(Map<K, List<V>> map, Function<List<V>, V> strategy) {
        Map<K, V> singleMap = new HashMap<>();
        if (map != null) {
            map.forEach((k, v) -> {
                singleMap.put(k, strategy.apply(v));
            });
        }
        return singleMap;
    }

    public static <K, V> Map<K, V> asSingleValueMap(Map<K, List<V>> map) {
        return asSingleValueMap(map, list -> list != null ? list.get(0) : null);
    }

    public static <K, V> Map<K, List<V>> asMultiValueMap(Map<K, V> map) {
        LinkedHashMap<K, List<V>> multiMap = new LinkedHashMap<>();
        if (map != null) {
            map.forEach((k, v) -> multiMap.put(k, Collections.singletonList(v)));
        }
        return multiMap;
    }
}
