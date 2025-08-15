package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Color;

public interface FrameOps<T extends FrameOps<T>> {

    T clear(FrameBuffer mask);

    T clear(FrameBuffer mask1, FrameBuffer mask2);

    T clear(FrameBuffer mask, FrameBuffer mask2, FrameBuffer mask3);

    T setClearColor(float red, float blue, float green, float alpha);

    default T setClearColor(Color color) {
        return setClearColor(color.red(), color.green(), color.blue(), color.alpha());
    }

    Color getClearColor();

    T setClearDepth(float depth);

    float getClearDepth();

    T setClearStencil(int s);

    int getClearStencil();

    T selectDrawBuffer(FrameColorBuffer frameColorBuffer);

    T selectDrawBuffers(FrameColorBuffer.MultiSelectable... drawBuffers);
}
