package com.vanix.easygl.core.graphics;

public interface Feature<T extends Feature<T>> {
    T enable();
    T disable();
}
