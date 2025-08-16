package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;

public sealed interface Attachment extends IntEnum permits ColorAttachment, Attachment.Renderable {
    Renderable Depth = new AttachmentImplR("DEPTH_ATTACHMENT");
    Renderable Stencil = new AttachmentImplR("STENCIL_ATTACHMENT");
    Renderable DepthStencil = new AttachmentImplR("DEPTH_STENCIL_ATTACHMENT");

    sealed interface Renderable extends Attachment permits AttachmentImplR, ColorAttachment {
    }
}
