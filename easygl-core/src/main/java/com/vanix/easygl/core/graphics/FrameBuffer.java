package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum FrameBuffer {
    Color("COLOR_BUFFER_BIT"), //
    Depth("DEPTH_BUFFER_BIT"), //
    Stencil("STENCIL_BUFFER_BIT");

    private final int value;

    FrameBuffer(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
