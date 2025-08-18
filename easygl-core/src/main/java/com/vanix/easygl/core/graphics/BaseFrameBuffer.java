package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.commons.Rectangle;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.MultiTargetBindable;
import org.joml.Vector4f;
import org.joml.Vector4i;

public interface BaseFrameBuffer<T extends BaseFrameBuffer<T>> extends MultiTargetBindable<BaseFrameBuffer.Target<T>, T> {

    default T attach(Target<T> target, FrameInnerBuffer.Attachment attachment, Texture2D texture2D) {
        return attach(target, attachment, texture2D, 0);
    }

    T attach(Target<T> target, FrameInnerBuffer.Attachment attachment, Texture2D texture2D, int level);

    T attach(Target<T> target, FrameInnerBuffer.Attachment attachment, RenderBuffer renderBuffer);

    T detachRenderBuffer(Target<T> target, FrameInnerBuffer.Attachment attachment);

    T blit(int srcX0, int srcY0, int srcX1, int srcY1,
           int dstX0, int dstY0, int dstX1, int dstY1,
           FrameInnerBuffer.Mask buffers, MagFilter filter);

    default T blit(Rectangle src, Rectangle dst, FrameInnerBuffer.Mask buffers, MagFilter filter) {
        return blit(src.getX(), src.getY(), src.getX() + src.getWidth(), src.getY() + src.getHeight(),
                dst.getX(), dst.getY(), dst.getX() + dst.getWidth(), dst.getY() + dst.getHeight(),
                buffers, filter);
    }

    T selectDrawBuffers(FrameInnerBuffer.MultiSelectableDrawBuffer... drawBuffers);

    //region Clearing the Buffers
    T clear(FrameInnerBuffer.Mask mask);

    T setClearColor(float red, float blue, float green, float alpha);

    default T setClearColor(Color color) {
        return setClearColor(color.red(), color.green(), color.blue(), color.alpha());
    }

    Color getClearColor();

    T clearColorBuffer(FrameInnerBuffer.DrawBuffer drawBuffer, Vector4f color);

    T clearColorBuffer(FrameInnerBuffer.DrawBuffer drawBuffer, Vector4i color);

    T clearColorBufferUnsigned(FrameInnerBuffer.DrawBuffer drawBuffer, Vector4i color);

    T clearDepthBuffer(float value);

    T clearStencilBuffer(int value);

    T clearDepthAndStencilBuffer(float depthValue, int stencilValue);

    T setClearDepth(float depth);

    float getClearDepth();

    T setClearStencil(int s);

    int getClearStencil();
    //endregion

    //region Fine Control of Buffer Updates
    T setColorMask(boolean read, boolean green, boolean blue, boolean alpha);

    T setDepthMask(boolean flag);

    T setStencilMask(int mask);

    T setStencilMask(Face face, int mask);
    //endregion


    sealed interface Target<T extends BaseFrameBuffer<T>> extends BindTarget<Target<T>, T> permits FrameBufferTarget {
        @SuppressWarnings("unchecked")
        static <T extends BaseFrameBuffer<T>> Target<T> read() {
            return FrameBufferTarget.Read;
        }

        @SuppressWarnings("unchecked")
        static <T extends BaseFrameBuffer<T>> Target<T> draw() {
            return FrameBufferTarget.Draw;
        }
    }

}
