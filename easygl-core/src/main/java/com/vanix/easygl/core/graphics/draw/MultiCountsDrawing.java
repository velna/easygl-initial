package com.vanix.easygl.core.graphics.draw;

public interface MultiCountsDrawing<S> extends Drawing<S> {
    Object counts();

    int drawCount();
}
