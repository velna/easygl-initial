package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.*;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.commons.util.TypeReference;
import org.joml.Vector4f;
import org.joml.Vector4i;

public interface FrameBuffer extends Bindable<FrameBuffer.Type, FrameBuffer>, Handle {
    BindableMeta<Type, FrameBuffer> Meta = MetaSystem.Graphics.of(FrameBuffer.class, new TypeReference<>() {
    });

    enum Type implements BindTarget<Type, FrameBuffer> {
        FrameBuffer(MetaSystem.Graphics.queryInt("FRAMEBUFFER")),
        ReadFrameBuffer(MetaSystem.Graphics.queryInt("READ_FRAMEBUFFER")),
        DrawFrameBuffer(MetaSystem.Graphics.queryInt("DRAW_FRAMEBUFFER"));

        private final int target;
        private final BindingState<Type, FrameBuffer> state;

        Type(int target) {
            this.target = target;
            state = Meta.newBindingState(name(), this);
        }

        @Override
        public int value() {
            return target;
        }

        @Override
        public BindingState<FrameBuffer.Type, FrameBuffer> state() {
            return state;
        }
    }

    enum Attachment {
        Color0(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT0")),
        Color1(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT1")),
        Color2(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT2")),
        Color3(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT3")),
        Color4(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT4")),
        Color5(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT5")),
        Color6(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT6")),
        Color7(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT7")),
        Color8(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT8")),
        Color9(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT9")),
        Color10(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT10")),
        Color11(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT11")),
        Color12(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT12")),
        Color13(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT13")),
        Color14(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT14")),
        Color15(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT15")),
        Color16(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT16")),
        Color17(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT17")),
        Color18(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT18")),
        Color19(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT19")),
        Color20(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT20")),
        Color21(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT21")),
        Color22(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT22")),
        Color23(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT23")),
        Color24(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT24")),
        Color25(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT25")),
        Color26(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT26")),
        Color27(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT27")),
        Color28(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT28")),
        Color29(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT29")),
        Color30(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT30")),
        Color31(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT31")),
        Depth(MetaSystem.Graphics.queryInt("DEPTH_ATTACHMENT")),
        Stencil(MetaSystem.Graphics.queryInt("STENCIL_ATTACHMENT")),
        DepthStencil(MetaSystem.Graphics.queryInt("DEPTH_STENCIL_ATTACHMENT"));

        private final int value;

        private Attachment(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

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
        return Meta.create(Type.FrameBuffer);
    }

    static CloseableArray<FrameBuffer> of(int n) {
        return Meta.createArray(n, Type.FrameBuffer);
    }

}
