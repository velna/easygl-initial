package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.primitives.Rectanglei;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.Support;

public interface FrameBuffer extends BaseFrameBuffer<FrameBuffer> {

    FrameBuffer selectDrawBuffer(FrameInnerBuffer.ColorBuffer colorBuffer);

    @Support(since = Version.GL43)
    FrameBuffer invalidate(Target<FrameBuffer> target, int x, int y, int width, int height, FrameInnerBuffer.Attachment attachment);

    default FrameBuffer invalidate(Target<FrameBuffer> target, Rectanglei<?> rectangle, FrameInnerBuffer.Attachment attachment) {
        return invalidate(target, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), attachment);
    }

    @Support(since = Version.GL43)
    FrameBuffer setDefaultWidth(int width);

    @Support(since = Version.GL43)
    int getDefaultWidth();

    @Support(since = Version.GL43)
    FrameBuffer setDefaultHeight(int height);

    @Support(since = Version.GL43)
    int getDefaultHeight();

    @Support(since = Version.GL43)
    FrameBuffer setDefaultLayers(int layers);

    @Support(since = Version.GL43)
    int getDefaultLayers();

    @Support(since = Version.GL43)
    FrameBuffer setDefaultSamples(int samples);

    @Support(since = Version.GL43)
    int getDefaultSamples();

    @Support(since = Version.GL43)
    FrameBuffer setDefaultFixedSampleLocations(int fixedSampleLocations);

    @Support(since = Version.GL43)
    int getDefaultFixedSampleLocations();

    static FrameBuffer of() {
        return MetaHolder.FrameBuffer.create();
    }

    static HandleArray<FrameBuffer> of(int n) {
        return MetaHolder.FrameBuffer.createArray(n);
    }

}
