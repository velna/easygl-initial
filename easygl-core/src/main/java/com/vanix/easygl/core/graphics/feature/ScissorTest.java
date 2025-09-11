package com.vanix.easygl.core.graphics.feature;

import com.vanix.easygl.commons.Rectangle;
import com.vanix.easygl.core.IndexedFeature;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.graphics.Version;

import java.nio.IntBuffer;

public interface ScissorTest extends IndexedFeature<ScissorTest, Graphics>, GraphicsFeature<ScissorTest> {

    default ScissorTest setBox(int[] box) {
        return setBox(box[0], box[1], box[2], box[3]);
    }

    default ScissorTest setBox(IntBuffer box) {
        return setBox(box.get(), box.get(), box.get(), box.get());
    }

    default ScissorTest setBox(Rectangle box) {
        return setBox(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

    ScissorTest setBox(int x, int y, int width, int height);

    @Support(since = Version.GL41)
    ScissorTest setBoxes(int first, int[] boxes);

    @Support(since = Version.GL41)
    ScissorTest setBoxes(int first, IntBuffer boxes);

    @Support(since = Version.GL41)
    ScissorTest setBoxes(int first, Rectangle... boxes);

    @Support(since = Version.GL41)
    ScissorTest setBoxAt(int index, int[] box);

    @Support(since = Version.GL41)
    ScissorTest setBoxAt(int index, IntBuffer box);

    @Support(since = Version.GL41)
    ScissorTest setBoxAt(int index, Rectangle box);

    @Support(since = Version.GL41)
    ScissorTest setBoxAt(int index, int x, int y, int width, int height);

    @Support(since = Version.GL41)
    Rectangle getBoxAt(int index);

    Rectangle getBox();
}
