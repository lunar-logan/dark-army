package com.anurag.darkarmy.common.util.collection;

import java.util.Objects;

public class Tuple3<P, Q, R> extends Tuple2<P, Q> {
    protected final R third;

    public Tuple3(P first, Q second, R third) {
        super(first, second);
        this.third = third;
    }

    public R getThird() {
        return third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
        return Objects.equals(third, tuple3.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), third);
    }

    @Override
    public String toString() {
        return "Tuple3{" + first + ", " + second + ", " + third + "}";
    }
}
