package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum SpriteCoordOrigin implements IntEnum {
    LowerLeft("LOWER_LEFT"),
    UpperLeft("UPPER_LEFT");
    private final int value;

    SpriteCoordOrigin(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
