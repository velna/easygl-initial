package com.vanix.easygl.core;

public interface Feature<T extends Feature<T>> {
    T enable();
    T disable();
}
