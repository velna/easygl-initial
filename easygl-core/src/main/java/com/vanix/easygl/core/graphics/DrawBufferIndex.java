package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum DrawBufferIndex {
    Index0(MetaSystem.Graphics.queryInt("DRAW_BUFFER0")),
    Index1(MetaSystem.Graphics.queryInt("DRAW_BUFFER1")),
    Index2(MetaSystem.Graphics.queryInt("DRAW_BUFFER2")),
    Index3(MetaSystem.Graphics.queryInt("DRAW_BUFFER3")),
    Index4(MetaSystem.Graphics.queryInt("DRAW_BUFFER4")),
    Index5(MetaSystem.Graphics.queryInt("DRAW_BUFFER5")),
    Index6(MetaSystem.Graphics.queryInt("DRAW_BUFFER6")),
    Index7(MetaSystem.Graphics.queryInt("DRAW_BUFFER7")),
    Index8(MetaSystem.Graphics.queryInt("DRAW_BUFFER8")),
    Index9(MetaSystem.Graphics.queryInt("DRAW_BUFFER9")),
    Index10(MetaSystem.Graphics.queryInt("DRAW_BUFFER10")),
    Index11(MetaSystem.Graphics.queryInt("DRAW_BUFFER11")),
    Index12(MetaSystem.Graphics.queryInt("DRAW_BUFFER12")),
    Index13(MetaSystem.Graphics.queryInt("DRAW_BUFFER13")),
    Index14(MetaSystem.Graphics.queryInt("DRAW_BUFFER14")),
    Index15(MetaSystem.Graphics.queryInt("DRAW_BUFFER15"));
    private final int value;

    DrawBufferIndex(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
