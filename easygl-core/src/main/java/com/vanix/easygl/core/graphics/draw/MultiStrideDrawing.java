package com.vanix.easygl.core.graphics.draw;

public interface MultiStrideDrawing<S> extends IndirectDrawing<S> {
    int drawCount();

    int stride();
}
