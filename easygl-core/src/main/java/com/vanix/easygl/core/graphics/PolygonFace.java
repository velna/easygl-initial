package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum PolygonFace {
    Front("FRONT"),
    Back("BACK"),
    FrontAndBack("FRONT_AND_BACK");
    private final int value;

    PolygonFace(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
