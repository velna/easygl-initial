package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Rectangle;
import com.vanix.easygl.core.IndexedFeature;

import java.nio.IntBuffer;

public interface ScissorTest extends IndexedFeature<ScissorTest, Graphics> {

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

    ScissorTest setBoxes(int first, int[] boxes);

    ScissorTest setBoxes(int first, IntBuffer boxes);

    ScissorTest setBoxes(int first, Rectangle... boxes);

    ScissorTest setBoxAt(int index, int[] box);

    ScissorTest setBoxAt(int index, IntBuffer box);

    ScissorTest setBoxAt(int index, Rectangle box);

    ScissorTest setBoxAt(int index, int x, int y, int width, int height);

    Rectangle getBoxAt(int index);

    Rectangle getBox();
}
