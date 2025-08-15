package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

final class DefaultDrawBuffer extends SimpleIntEnum implements DrawBuffer {
    public DefaultDrawBuffer(String id) {
        super(MetaSystem.Graphics.queryInt(id));
    }
}
