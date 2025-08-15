package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;

public sealed interface Attachment extends IntEnum permits BufferAttachment, ColorAttachment {
    Attachment Depth = new BufferAttachment("DEPTH_ATTACHMENT");
    Attachment Stencil = new BufferAttachment("STENCIL_ATTACHMENT");
    Attachment DepthStencil = new BufferAttachment("DEPTH_STENCIL_ATTACHMENT");
}
