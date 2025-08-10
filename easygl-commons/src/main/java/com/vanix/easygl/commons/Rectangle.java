package com.vanix.easygl.commons;

public interface Rectangle extends Dimension {
    int getX();

    int getY();

    default Rectangle toRectangle() {
        return this;
    }

    static Rectangle of(int x, int y, int width, int height){
        return new SimpleRectangle(x, y, width, height);
    }
}
