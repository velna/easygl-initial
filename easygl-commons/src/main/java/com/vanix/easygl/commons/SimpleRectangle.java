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

    public SimpleRectangle(int[] rect) {
        this(rect[0], rect[1], rect[2], rect[3]);
    }
}
