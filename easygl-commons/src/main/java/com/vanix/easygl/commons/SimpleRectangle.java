package com.vanix.easygl.commons;

import lombok.Getter;

@Getter
public class SimpleRectangle extends SimpleDimension implements Rectangle {
    private final int x;
    private final int y;

    public SimpleRectangle(int x, int y, int width, int height) {
        super(width, height);
        this.x = x;
        this.y = y;
    }
}
