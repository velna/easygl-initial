package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.CloseableArray;
import com.vanix.easygl.core.MultiTargetBindable;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import org.joml.Vector4f;
import org.joml.Vector4i;

public interface FrameBuffer extends MultiTargetBindable<FrameBuffer.Target, FrameBuffer>, FrameBufferOps<FrameBuffer> {
    BindableMeta<Target, FrameBuffer> Meta = MetaSystem.Graphics.of(FrameBuffer.class);

    enum Target implements BindTarget<Target, FrameBuffer> {
        FrameBuffer("FRAMEBUFFER"),
        ReadFrameBuffer("READ_FRAMEBUFFER"),
        DrawFrameBuffer("DRAW_FRAMEBUFFER");

        private final int target;
        private final BindingState<Target, FrameBuffer> state;

        Target(String id) {
            this.target = MetaSystem.Graphics.queryInt(id);
            state = Meta.newBindingState(name());
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
//
//    default FrameBuffer attach(Attachment attachment, TextureCube textureCube){
//        return attach(attachment, textureCube, 0);
//    }

    FrameBuffer attach(Target target, Attachment attachment, RenderBuffer renderBuffer);

    FrameBuffer clearColor(DrawBufferIndex index, Vector4f color);

    FrameBuffer clearColor(DrawBufferIndex index, Vector4i color);

    FrameBuffer clearColorUnsigned(DrawBufferIndex index, Vector4i color);

    static FrameBuffer of() {
        return Meta.create();
    }

    static CloseableArray<FrameBuffer> of(int n) {
        return Meta.createArray(n);
    }

}
