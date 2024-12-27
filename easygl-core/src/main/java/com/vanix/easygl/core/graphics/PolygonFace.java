package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;

public enum PolygonFace {
    Front(GLC.GL_FRONT),
    Back(GLC.GL_BACK),
    FrontAndBack(GLC.GL_FRONT_AND_BACK);
    private final int value;

    PolygonFace(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
