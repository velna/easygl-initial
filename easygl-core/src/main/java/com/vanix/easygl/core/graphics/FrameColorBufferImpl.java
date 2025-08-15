package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

final class FrameColorBufferImpl extends SimpleIntEnum implements FrameColorBuffer {
    public FrameColorBufferImpl(String id) {
        super(MetaSystem.Graphics.queryInt(id));
    }
}
