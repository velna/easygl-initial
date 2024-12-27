package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;
import com.vanix.easygl.core.graphics.gl.GlFrameBuffer;
import org.joml.Vector4f;
import org.joml.Vector4i;

public interface FrameBuffer extends Bindable<FrameBuffer>, Handle {

    BindingState State = new BindingState("FrameBuffer");

    enum Type {
        FrameBuffer(GLC.GL_FRAMEBUFFER);

        private final int value;

        private Type(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    enum Attachment {
        Color0(GLC.GL_COLOR_ATTACHMENT0),
        Color1(GLC.GL_COLOR_ATTACHMENT1),
        Color2(GLC.GL_COLOR_ATTACHMENT2),
        Color3(GLC.GL_COLOR_ATTACHMENT3),
        Color4(GLC.GL_COLOR_ATTACHMENT4),
        Color5(GLC.GL_COLOR_ATTACHMENT5),
        Color6(GLC.GL_COLOR_ATTACHMENT6),
        Color7(GLC.GL_COLOR_ATTACHMENT7),
        Color8(GLC.GL_COLOR_ATTACHMENT8),
        Color9(GLC.GL_COLOR_ATTACHMENT9),
        Color10(GLC.GL_COLOR_ATTACHMENT10),
        Color11(GLC.GL_COLOR_ATTACHMENT11),
        Color12(GLC.GL_COLOR_ATTACHMENT12),
        Color13(GLC.GL_COLOR_ATTACHMENT13),
        Color14(GLC.GL_COLOR_ATTACHMENT14),
        Color15(GLC.GL_COLOR_ATTACHMENT15),
        Color16(GLC.GL_COLOR_ATTACHMENT16),
        Color17(GLC.GL_COLOR_ATTACHMENT17),
        Color18(GLC.GL_COLOR_ATTACHMENT18),
        Color19(GLC.GL_COLOR_ATTACHMENT19),
        Color20(GLC.GL_COLOR_ATTACHMENT20),
        Color21(GLC.GL_COLOR_ATTACHMENT21),
        Color22(GLC.GL_COLOR_ATTACHMENT22),
        Color23(GLC.GL_COLOR_ATTACHMENT23),
        Color24(GLC.GL_COLOR_ATTACHMENT24),
        Color25(GLC.GL_COLOR_ATTACHMENT25),
        Color26(GLC.GL_COLOR_ATTACHMENT26),
        Color27(GLC.GL_COLOR_ATTACHMENT27),
        Color28(GLC.GL_COLOR_ATTACHMENT28),
        Color29(GLC.GL_COLOR_ATTACHMENT29),
        Color30(GLC.GL_COLOR_ATTACHMENT30),
        Color31(GLC.GL_COLOR_ATTACHMENT31),
        Depth(GLC.GL_DEPTH_ATTACHMENT),
        Stencil(GLC.GL_STENCIL_ATTACHMENT),
        DepthStencil(GLC.GL_DEPTH_STENCIL_ATTACHMENT);

        private final int value;

        private Attachment(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    FrameBuffer readOnly();

    FrameBuffer writeOnly();

    FrameBuffer readWrite();

    default FrameBuffer attach(Attachment attachment, Texture2D texture2D) {
        return attach(attachment, texture2D, 0);
    }

    FrameBuffer attach(Attachment attachment, Texture2D texture2D, int level);
//
//    default FrameBuffer attach(Attachment attachment, TextureCube textureCube){
//        return attach(attachment, textureCube, 0);
//    }

    FrameBuffer attach(Attachment attachment, RenderBuffer renderBuffer);

    FrameBuffer clearColor(DrawBufferIndex index, Vector4f color);

    FrameBuffer clearColor(DrawBufferIndex index, Vector4i color);

    FrameBuffer clearColorUnsigned(DrawBufferIndex index, Vector4i color);

    FrameBuffer clearDepth(float value);

    FrameBuffer clearStencil(int value);

    static FrameBuffer of() {
        return new GlFrameBuffer();
    }

    static FrameBuffer defaultBuffer() {
        return GlFrameBuffer.DefaultFrameBuffer;
    }
}
