package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.CloseableArray;
import com.vanix.easygl.core.MultiTargetBindable;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import org.joml.Vector4f;
import org.joml.Vector4i;

public interface Frame extends MultiTargetBindable<Frame.Target, Frame>, FrameOps<Frame> {
    BindableMeta<Target, Frame> Meta = MetaSystem.Graphics.of(Frame.class);

    enum Target implements BindTarget<Target, Frame> {
        ReadWrite("FRAMEBUFFER"),
        Read("READ_FRAMEBUFFER"),
        Write("DRAW_FRAMEBUFFER");

        private final int target;
        private final BindingState<Target, Frame> state;

        Target(String id) {
            this.target = MetaSystem.Graphics.queryInt(id);
            state = Meta.newBindingState(name());
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
//
//    default FrameBuffer attach(Attachment attachment, TextureCube textureCube){
//        return attach(attachment, textureCube, 0);
//    }

    Frame attach(Target target, FrameAttachment.Renderable attachment, RenderBuffer renderBuffer);

    Frame clearColor(DrawBufferIndex index, Vector4f color);

    Frame clearColor(DrawBufferIndex index, Vector4i color);

    Frame clearColorUnsigned(DrawBufferIndex index, Vector4i color);

    static Frame of() {
        return Meta.create();
    }

    static CloseableArray<Frame> of(int n) {
        return Meta.createArray(n);
    }

}
