package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;

public enum PolygonMode {
    Point(GLC.GL_POINT),
    Line(GLC.GL_LINE),
    Fill(GLC.GL_FILL);
    private final int value;

    PolygonMode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
