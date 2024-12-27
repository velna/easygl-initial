package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;

public enum DrawBufferIndex {
    Index0(GLC.GL_DRAW_BUFFER0),
    Index1(GLC.GL_DRAW_BUFFER1),
    Index2(GLC.GL_DRAW_BUFFER2),
    Index3(GLC.GL_DRAW_BUFFER3),
    Index4(GLC.GL_DRAW_BUFFER4),
    Index5(GLC.GL_DRAW_BUFFER5),
    Index6(GLC.GL_DRAW_BUFFER6),
    Index7(GLC.GL_DRAW_BUFFER7),
    Index8(GLC.GL_DRAW_BUFFER8),
    Index9(GLC.GL_DRAW_BUFFER9),
    Index10(GLC.GL_DRAW_BUFFER10),
    Index11(GLC.GL_DRAW_BUFFER11),
    Index12(GLC.GL_DRAW_BUFFER12),
    Index13(GLC.GL_DRAW_BUFFER13),
    Index14(GLC.GL_DRAW_BUFFER14),
    Index15(GLC.GL_DRAW_BUFFER15);
    private final int value;

    DrawBufferIndex(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
