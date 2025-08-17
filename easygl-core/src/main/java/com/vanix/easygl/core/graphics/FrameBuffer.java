package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.commons.Rectangle;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.MultiTargetBindable;
import com.vanix.easygl.core.meta.MetaSystem;
import org.joml.Vector4f;
import org.joml.Vector4i;

public interface FrameBuffer extends MultiTargetBindable<FrameBuffer.Target, FrameBuffer> {

    enum Target implements BindTarget<Target, FrameBuffer> {
        Read("READ_FRAMEBUFFER"),
        Draw("DRAW_FRAMEBUFFER");

        private final int target;
        private final BindingState<Target, FrameBuffer> state;

        Target(String id) {
            this.target = MetaSystem.Graphics.queryInt(id);
            state = MetaHolder.FrameBuffer.newBindingState(name());
        }

        @Override
        public int value() {
            return target;
        }

        @Override
        public BindingState<Target, FrameBuffer> state() {
            return state;
        }
    }

    default FrameBuffer attach(Target target, Attachment attachment, Texture2D texture2D) {
        return attach(target, attachment, texture2D, 0);
    }

    FrameBuffer attach(Target target, Attachment attachment, Texture2D texture2D, int level);

    FrameBuffer attach(Target target, Attachment.Renderable attachment, RenderBuffer renderBuffer);

    FrameBuffer detachRenderBuffer(Target target, Attachment.Renderable attachment);

    //region Selecting Buffers for Writing
    FrameBuffer selectDrawBuffer(ColorBuffer colorBuffer);

    FrameBuffer selectDrawBuffers(ColorBuffer.MultiSelectable... drawBuffers);
    //endregion

    FrameBuffer blit(int srcX0, int srcY0, int srcX1, int srcY1,
                     int dstX0, int dstY0, int dstX1, int dstY1,
                     FrameInnerBufferMask buffers, MagFilter filter);

    default FrameBuffer blit(Rectangle src, Rectangle dst, FrameInnerBufferMask buffers, MagFilter filter) {
        return blit(src.getX(), src.getY(), src.getX() + src.getWidth(), src.getY() + src.getHeight(),
                dst.getX(), dst.getY(), dst.getX() + dst.getWidth(), dst.getY() + dst.getHeight(),
                buffers, filter);
    }

    //region Clearing the Buffers
    FrameBuffer clear(FrameInnerBufferMask mask);

    FrameBuffer setClearColor(float red, float blue, float green, float alpha);

    default FrameBuffer setClearColor(Color color) {
        return setClearColor(color.red(), color.green(), color.blue(), color.alpha());
    }

    Color getClearColor();

    FrameBuffer clearColorBuffer(DrawBuffer drawBuffer, Vector4f color);
    FrameBuffer clearColorBuffer(DrawBuffer drawBuffer, Vector4i color);
    FrameBuffer clearColorBufferUnsigned(DrawBuffer drawBuffer, Vector4i color);

    FrameBuffer clearDepthBuffer(float value);

    FrameBuffer clearStencilBuffer(int value);

    FrameBuffer clearDepthAndStencilBuffer(float depthValue, int stencilValue);

    FrameBuffer setClearDepth(float depth);

    float getClearDepth();

    FrameBuffer setClearStencil(int s);

    int getClearStencil();
    //endregion

    //region Fine Control of Buffer Updates
    FrameBuffer setColorMask(boolean read, boolean green, boolean blue, boolean alpha);

    FrameBuffer setDepthMask(boolean flag);

    FrameBuffer setStencilMask(int mask);

    FrameBuffer setStencilMask(Face face, int mask);
    //endregion

    static FrameBuffer of() {
        return MetaHolder.FrameBuffer.create();
    }

    static HandleArray<FrameBuffer> of(int n) {
        return MetaHolder.FrameBuffer.createArray(n);
    }

}
