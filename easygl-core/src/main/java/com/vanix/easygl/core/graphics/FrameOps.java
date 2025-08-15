package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.commons.Rectangle;

public interface FrameOps<T extends FrameOps<T>> {

    T clear(FrameBuffers mask);

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

    T blit(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, FrameBuffers buffers, Texture.MagFilter filter);

    default T blit(Rectangle src, Rectangle dst, FrameBuffers buffers, Texture.MagFilter filter) {
        return blit(src.getX(), src.getY(), src.getX() + src.getWidth(), src.getY() + src.getHeight(),
                dst.getX(), dst.getY(), dst.getX() + dst.getWidth(), dst.getY() + dst.getHeight(),
                buffers, filter);
    }
}
