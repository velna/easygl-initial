package com.vanix.easygl.commons;

public interface Dimension {
    int getWidth();

    int getHeight();

    default Dimension getDimension() {
        return this;
    }

}
