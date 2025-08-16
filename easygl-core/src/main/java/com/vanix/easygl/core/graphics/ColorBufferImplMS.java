package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

final class ColorBufferImplMS extends SimpleIntEnum implements ColorBuffer.MultiSelectable {
    public ColorBufferImplMS(String id) {
        super(MetaSystem.Graphics.queryInt(id));
    }
}
