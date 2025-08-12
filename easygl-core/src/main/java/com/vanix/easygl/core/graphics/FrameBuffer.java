package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.*;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import org.joml.Vector4f;
import org.joml.Vector4i;

public interface FrameBuffer extends Bindable<FrameBuffer.Type, FrameBuffer>, Handle {
    BindableMeta<Type, FrameBuffer> Meta = MetaSystem.Graphics.of(FrameBuffer.class);

    enum Type implements BindTarget<Type, FrameBuffer> {
        FrameBuffer("FRAMEBUFFER"),
        ReadFrameBuffer("READ_FRAMEBUFFER"),
        DrawFrameBuffer("DRAW_FRAMEBUFFER");

        private final int target;
        private final BindingState<Type, FrameBuffer> state;

        Type(String id) {
            this.target = MetaSystem.Graphics.queryInt(id);
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
        Color0("COLOR_ATTACHMENT0"),
        Color1("COLOR_ATTACHMENT1"),
        Color2("COLOR_ATTACHMENT2"),
        Color3("COLOR_ATTACHMENT3"),
        Color4("COLOR_ATTACHMENT4"),
        Color5("COLOR_ATTACHMENT5"),
        Color6("COLOR_ATTACHMENT6"),
        Color7("COLOR_ATTACHMENT7"),
        Color8("COLOR_ATTACHMENT8"),
        Color9("COLOR_ATTACHMENT9"),
        Color10("COLOR_ATTACHMENT10"),
        Color11("COLOR_ATTACHMENT11"),
        Color12("COLOR_ATTACHMENT12"),
        Color13("COLOR_ATTACHMENT13"),
        Color14("COLOR_ATTACHMENT14"),
        Color15("COLOR_ATTACHMENT15"),
        Color16("COLOR_ATTACHMENT16"),
        Color17("COLOR_ATTACHMENT17"),
        Color18("COLOR_ATTACHMENT18"),
        Color19("COLOR_ATTACHMENT19"),
        Color20("COLOR_ATTACHMENT20"),
        Color21("COLOR_ATTACHMENT21"),
        Color22("COLOR_ATTACHMENT22"),
        Color23("COLOR_ATTACHMENT23"),
        Color24("COLOR_ATTACHMENT24"),
        Color25("COLOR_ATTACHMENT25"),
        Color26("COLOR_ATTACHMENT26"),
        Color27("COLOR_ATTACHMENT27"),
        Color28("COLOR_ATTACHMENT28"),
        Color29("COLOR_ATTACHMENT29"),
        Color30("COLOR_ATTACHMENT30"),
        Color31("COLOR_ATTACHMENT31"),
        Depth("DEPTH_ATTACHMENT"),
        Stencil("STENCIL_ATTACHMENT"),
        DepthStencil("DEPTH_STENCIL_ATTACHMENT");

        private final int value;

        Attachment(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
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
