package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.core.meta.MetaSystem;

public interface FrameBufferOps<T extends FrameBufferOps<T>> {

    enum BufferMask {
        Color("COLOR_BUFFER_BIT"), //
        Depth("DEPTH_BUFFER_BIT"), //
        Stencil("STENCIL_BUFFER_BIT");

        private final int value;

        BufferMask(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    T clear(FrameBufferOps.BufferMask mask);

    T clear(FrameBufferOps.BufferMask mask1, FrameBufferOps.BufferMask mask2);

    T clear(FrameBufferOps.BufferMask mask, FrameBufferOps.BufferMask mask2, FrameBufferOps.BufferMask mask3);

    T setClearColor(float red, float blue, float green, float alpha);

    default T setClearColor(Color color) {
        return setClearColor(color.red(), color.green(), color.blue(), color.alpha());
    }

    Color getClearColor();

    T setClearDepth(float depth);

    float getClearDepth();

    T setClearStencil(int s);

    int getClearStencil();

    T selectDrawBuffer(DrawBuffer drawBuffer);

    T selectDrawBuffers(DrawBuffer.MultiSelectable... drawBuffers);
}
