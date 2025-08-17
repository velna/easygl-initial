package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum MagFilter {
    Nearest("NEAREST"),
    Linear("LINEAR");

    private final int value;

    MagFilter(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
