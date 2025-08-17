package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum MinFilter {
    Nearest("NEAREST"),
    Linear("LINEAR"),
    NearestMipmapNearest("NEAREST_MIPMAP_NEAREST"),
    LinearMipmapNearest("LINEAR_MIPMAP_NEAREST"),
    NearestMipmapLinear("NEAREST_MIPMAP_LINEAR"),
    LinearMipmapLinear("LINEAR_MIPMAP_LINEAR");

    private final int value;

    MinFilter(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
