package com.vanix.easygl.core.graphics.feature;

public interface Feature<T extends Feature<T>> {
    T enable();
    T disable();
}
