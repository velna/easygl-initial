package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum DrawBufferIndex {
    Index0("DRAW_BUFFER0"),
    Index1("DRAW_BUFFER1"),
    Index2("DRAW_BUFFER2"),
    Index3("DRAW_BUFFER3"),
    Index4("DRAW_BUFFER4"),
    Index5("DRAW_BUFFER5"),
    Index6("DRAW_BUFFER6"),
    Index7("DRAW_BUFFER7"),
    Index8("DRAW_BUFFER8"),
    Index9("DRAW_BUFFER9"),
    Index10("DRAW_BUFFER10"),
    Index11("DRAW_BUFFER11"),
    Index12("DRAW_BUFFER12"),
    Index13("DRAW_BUFFER13"),
    Index14("DRAW_BUFFER14"),
    Index15("DRAW_BUFFER15");
    private final int value;

    DrawBufferIndex(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
