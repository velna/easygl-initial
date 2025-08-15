package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

final class BufferAttachment extends SimpleIntEnum implements Attachment {
    public BufferAttachment(String id) {
        super(MetaSystem.Graphics.queryInt(id));
    }
}
