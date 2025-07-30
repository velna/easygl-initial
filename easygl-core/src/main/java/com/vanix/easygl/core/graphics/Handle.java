package com.vanix.easygl.core.graphics;

public interface Handle extends Closeable {
    int handle();

    default long longHandle() {
        throw new UnsupportedOperationException();
    }
}
