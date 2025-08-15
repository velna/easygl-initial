package com.vanix.easygl.core;

public interface Feature<T extends Feature<T>> {
    boolean isEnabled();

    T enable();

    T disable();
}
