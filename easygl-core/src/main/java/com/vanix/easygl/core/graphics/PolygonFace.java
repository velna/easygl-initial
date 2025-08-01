package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum PolygonFace {
    Front(MetaSystem.Graphics.queryInt("FRONT")),
    Back(MetaSystem.Graphics.queryInt("BACK")),
    FrontAndBack(MetaSystem.Graphics.queryInt("FRONT_AND_BACK"));
    private final int value;

    PolygonFace(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
