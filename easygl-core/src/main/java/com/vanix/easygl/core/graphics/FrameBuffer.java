package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.Rectangle;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.Support;

public interface FrameBuffer extends BaseFrameBuffer<FrameBuffer> {

    FrameBuffer selectDrawBuffer(DrawBuffer drawBuffer);

    @Support(since = Version.GL43)
    FrameBuffer invalidate(Target<FrameBuffer> target, int x, int y, int width, int height, FrameInnerBuffer.Attachment attachment);

    default FrameBuffer invalidate(Target<FrameBuffer> target, Rectangle rectangle, FrameInnerBuffer.Attachment attachment) {
        return invalidate(target, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), attachment);
    }

    static FrameBuffer of() {
        return MetaHolder.FrameBuffer.create();
    }

    static HandleArray<FrameBuffer> of(int n) {
        return MetaHolder.FrameBuffer.createArray(n);
    }

    sealed interface DrawBuffer extends IntEnum permits FrameInnerBuffer.Constants, FrameInnerBuffer.ColorAttachmentImpl {
        DrawBuffer None = FrameInnerBuffer.Constants.None;

        static DrawBuffer ofColor(int i) {
            return FrameInnerBuffer.ColorAttachmentImpl.of(i);
        }
    }
}
