package com.vanix.easygl.commons;

public class BitSet<T extends IntEnum> {
    private int value;

    private BitSet(T intEnum) {
        value = intEnum.value();
    }

    public BitSet<T> or(T e) {
        value |= e.value();
        return this;
    }

    public BitSet<T> or(T e1, T e2) {
        value |= e1.value() | e2.value();
        return this;
    }

    public BitSet<T> or(T e1, T e2, T e3) {
        value |= e1.value() | e2.value() | e3.value();
        return this;
    }

    public BitSet<T> or(T e1, T e2, T e3, T e4) {
        value |= e1.value() | e2.value() | e3.value() | e4.value();
        return this;
    }

    public BitSet<T> or(T e1, T e2, T e3, T e4, T e5) {
        value |= e1.value() | e2.value() | e3.value() | e4.value() | e5.value();
        return this;
    }

    public int value() {
        return value;
    }

    public static <T extends IntEnum> BitSet<T> of(T intEnum) {
        return new BitSet<>(intEnum);
    }
}
