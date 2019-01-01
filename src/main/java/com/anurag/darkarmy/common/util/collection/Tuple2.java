package com.anurag.darkarmy.common.util.collection;

import java.util.Objects;

public class Tuple2<P, Q> {
    protected final P first;
    protected final Q second;

    public Tuple2(P first, Q second) {
        this.first = first;
        this.second = second;
    }

    public P getFirst() {
        return first;
    }

    public Q getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(first, tuple2.first) &&
                Objects.equals(second, tuple2.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Tuple2{" + first + ", " + second + "}";
    }

    public static <P, Q> Tuple2<P, Q> valueOf(P first, Q second) {
        return new Tuple2<>(first, second);
    }
}
