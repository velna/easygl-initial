package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

final class ColorBufferImpl extends SimpleIntEnum implements ColorBuffer {
    public ColorBufferImpl(String id) {
        super(MetaSystem.Graphics.queryInt(id));
    }
}
