package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum FrameInnerBufferMask {
    Color("COLOR_BUFFER_BIT"), //
    Depth("DEPTH_BUFFER_BIT"), //
    Stencil("STENCIL_BUFFER_BIT"),
    ColorAndDepth(Color, Depth),
    ColorAndStencil(Color, Stencil),
    DepthAndStencil(Depth, Stencil),
    All(Color, Depth, Stencil);

    private final int value;

    FrameInnerBufferMask(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    FrameInnerBufferMask(FrameInnerBufferMask... buffers) {
        int v = 0;
        for (var buf : buffers) {
            v |= buf.value;
        }
        this.value = v;
    }

    public int value() {
        return value;
    }
}
