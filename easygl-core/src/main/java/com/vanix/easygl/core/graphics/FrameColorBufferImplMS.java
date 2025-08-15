package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

final class FrameColorBufferImplMS extends SimpleIntEnum implements FrameColorBuffer.MultiSelectable {
    public FrameColorBufferImplMS(String id) {
        super(MetaSystem.Graphics.queryInt(id));
    }
}
