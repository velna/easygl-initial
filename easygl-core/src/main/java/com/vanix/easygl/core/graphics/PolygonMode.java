package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum PolygonMode {
    Point(MetaSystem.Graphics.queryInt("POINT")),
    Line(MetaSystem.Graphics.queryInt("LINE")),
    Fill(MetaSystem.Graphics.queryInt("FILL"));
    private final int value;

    PolygonMode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
