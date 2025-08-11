package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum PolygonMode {
    Point("POINT"),
    Line("LINE"),
    Fill("FILL");
    private final int value;

    PolygonMode(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
