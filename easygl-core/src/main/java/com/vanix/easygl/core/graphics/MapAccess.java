package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public enum MapAccess implements IntEnum {
    Read("MAP_READ_BIT"),
    Write("MAP_WRITE_BIT"),
    Persistent("MAP_PERSISTENT_BIT"),
    Coherent("MAP_COHERENT_BIT");
    private final int value;

    MapAccess(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    @Override
    public int value() {
        return value;
    }
}
