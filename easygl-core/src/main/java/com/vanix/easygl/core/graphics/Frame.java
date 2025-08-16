package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.commons.Rectangle;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.CloseableArray;
import com.vanix.easygl.core.MultiTargetBindable;
import com.vanix.easygl.core.meta.MetaSystem;
import org.joml.Vector4f;
import org.joml.Vector4i;

public interface Frame extends MultiTargetBindable<Frame.Target, Frame> {

    enum Target implements BindTarget<Target, Frame> {
        Read("READ_FRAMEBUFFER"),
        Draw("DRAW_FRAMEBUFFER");

        private final int target;
        private final BindingState<Target, Frame> state;

        Target(String id) {
            this.target = MetaSystem.Graphics.queryInt(id);
            state = MetaHolder.Frame.newBindingState(name());
        }

        @Override
        public int value() {
            return target;
        }

        @Override
        public BindingState<Target, Frame> state() {
            return state;
        }
    }

    default Frame attach(Target target, FrameAttachment attachment, Texture2D texture2D) {
        return attach(target, attachment, texture2D, 0);
    }

    Frame attach(Target target, FrameAttachment attachment, Texture2D texture2D, int level);

    Frame attach(Target target, FrameAttachment.Renderable attachment, RenderBuffer renderBuffer);

    Frame detachRenderBuffer(Target target, FrameAttachment.Renderable attachment);

    Frame clear(FrameBuffers mask);

    Frame setClearColor(float red, float blue, float green, float alpha);

    default Frame setClearColor(Color color) {
        return setClearColor(color.red(), color.green(), color.blue(), color.alpha());
    }

    Color getClearColor();

    Frame setClearDepth(float depth);

    float getClearDepth();

    Frame setClearStencil(int s);

    int getClearStencil();

    Frame selectDrawBuffer(FrameColorBuffer frameColorBuffer);

    Frame selectDrawBuffers(FrameColorBuffer.MultiSelectable... drawBuffers);

    Frame blit(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, FrameBuffers buffers, Texture.MagFilter filter);

    default Frame blit(Rectangle src, Rectangle dst, FrameBuffers buffers, Texture.MagFilter filter) {
        return blit(src.getX(), src.getY(), src.getX() + src.getWidth(), src.getY() + src.getHeight(),
                dst.getX(), dst.getY(), dst.getX() + dst.getWidth(), dst.getY() + dst.getHeight(),
                buffers, filter);
    }

    Frame clearColor(int colorAttachmentIndex, Vector4f color);

    Frame clearColor(int colorAttachmentIndex, Vector4i color);

    Frame clearColorUnsigned(int colorAttachmentIndex, Vector4i color);

    Frame clearDepth(float value);

    Frame clearStencil(int value);

    Frame clearDepthAndStencil(float depthValue, int stencilValue);

    static Frame of() {
        return MetaHolder.Frame.create();
    }

    static CloseableArray<Frame> of(int n) {
        return MetaHolder.Frame.createArray(n);
    }

}
