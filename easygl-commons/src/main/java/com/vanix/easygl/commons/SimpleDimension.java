package com.vanix.easygl.commons;

import lombok.Getter;

@Getter
public class SimpleDimension implements Dimension {
    private final int width;
    private final int height;

    public SimpleDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
