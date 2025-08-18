package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.commons.util.LazyList;
import com.vanix.easygl.core.meta.MetaSystem;

import java.util.ArrayList;
import java.util.List;

public abstract class FrameInnerBuffer {

    public sealed interface Attachment extends IntEnum permits ColorAttachment, Constants {
        int MAX_COLOR_ATTACHMENTS = MetaSystem.Graphics.queryInt("GET.MAX_COLOR_ATTACHMENTS");
        Attachment Depth = Constants.DepthAttachment;
        Attachment Stencil = Constants.StencilAttachment;
        Attachment DepthStencil = Constants.DepthStencilAttachment;

        static Attachment ofColor(int i) {
            return ColorAttachment.of(i);
        }
    }

    public sealed interface MultiSelectableDrawBuffer extends IntEnum permits Constants, ColorAttachment {
        MultiSelectableDrawBuffer None = FrameInnerBuffer.Constants.None;
        MultiSelectableDrawBuffer FrontLeft = FrameInnerBuffer.Constants.FrontLeft;
        MultiSelectableDrawBuffer FrontRight = FrameInnerBuffer.Constants.FrontRight;
        MultiSelectableDrawBuffer BackLeft = FrameInnerBuffer.Constants.BackLeft;
        MultiSelectableDrawBuffer BackRight = FrameInnerBuffer.Constants.BackRight;

        static MultiSelectableDrawBuffer ofColor(int i) {
            return ColorAttachment.of(i);
        }
    }

    public static final class DrawBuffer extends SimpleIntEnum {
        public static final int MAX = MetaSystem.Graphics.queryInt("GET.GL_MAX_DRAW_BUFFERS");
        private static final List<DrawBuffer> cache = LazyList.lazyList(new ArrayList<>(), DrawBuffer::new);
        private final int index;

        private DrawBuffer(int index) {
            super(MetaSystem.Graphics.queryInt("DRAW_BUFFER" + index));
            this.index = index;
        }

        public int index() {
            return index;
        }

        public static DrawBuffer of(int i) {
            if (i >= MAX || i < 0) {
                throw new IllegalArgumentException("Color attachment out of range: " + i);
            }
            return cache.get(i);
        }
    }

    public enum Mask {
        Color("COLOR_BUFFER_BIT"), //
        Depth("DEPTH_BUFFER_BIT"), //
        Stencil("STENCIL_BUFFER_BIT"),
        ColorAndDepth(Color, Depth),
        ColorAndStencil(Color, Stencil),
        DepthAndStencil(Depth, Stencil),
        All(Color, Depth, Stencil);

        private final int value;

        Mask(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        Mask(Mask... buffers) {
            int v = 0;
            for (var buf : buffers) {
                v |= buf.value;
            }
            this.value = v;
        }

        public int value() {
            return value;
        }
    }

    static final class ColorAttachment extends SimpleIntEnum implements Attachment, FrameBuffer.DrawBuffer, MultiSelectableDrawBuffer {
        private static final List<ColorAttachment> cache = LazyList.lazyList(new ArrayList<>(), ColorAttachment::new);

        private ColorAttachment(int index) {
            super(MetaSystem.Graphics.queryInt("COLOR_ATTACHMENT" + index));
        }

        static ColorAttachment of(int i) {
            if (i >= MAX_COLOR_ATTACHMENTS || i < 0) {
                throw new IllegalArgumentException("Color attachment out of range: " + i);
            }
            return cache.get(i);
        }
    }

    enum Constants implements
            Attachment,
            MultiSelectableDrawBuffer,
            DefaultFrameBuffer.DrawBuffer,
            DefaultFrameBuffer.Invalidatable,
            FrameBuffer.DrawBuffer {
        None("NONE"),
        FrontLeft("FRONT_LEFT"),
        FrontRight("FRONT_RIGHT"),
        BackLeft("BACK_LEFT"),
        BackRight("BACK_RIGHT"),
        Front("FRONT"),
        Back("BACK"),
        Left("LEFT"),
        Right("RIGHT"),
        FrontAndBack("FRONT_AND_BACK"),
        Aux0("AUX0"),
        Aux1("AUX1"),
        Aux2("AUX2"),
        Aux3("AUX3"),
        Color("COLOR"),
        Depth("DEPTH"),
        Stencil("STENCIL"),
        DepthAttachment("DEPTH_ATTACHMENT"),
        StencilAttachment("STENCIL_ATTACHMENT"),
        DepthStencilAttachment("DEPTH_STENCIL_ATTACHMENT");
        private final int value;

        Constants(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

}
