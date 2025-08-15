package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

final class FrameAttachmentImplR extends SimpleIntEnum implements FrameAttachment.Renderable {
    public FrameAttachmentImplR(String id) {
        super(MetaSystem.Graphics.queryInt(id));
    }
}
