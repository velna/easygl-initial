package com.vanix.easygl.commons;

public interface Dimension {
    int getWidth();

    int getHeight();

    default Dimension getDimension() {
        return this;
    }

    static Dimension of(int width, int height) {
        return new SimpleDimension(width, height);
    }
}
