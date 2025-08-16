package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

final class AttachmentImplR extends SimpleIntEnum implements Attachment.Renderable {
    public AttachmentImplR(String id) {
        super(MetaSystem.Graphics.queryInt(id));
    }
}
