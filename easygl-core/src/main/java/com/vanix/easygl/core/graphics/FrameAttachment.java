package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;

public sealed interface FrameAttachment extends IntEnum permits FrameColorAttachment, FrameAttachment.Renderable {
    Renderable Depth = new FrameAttachmentImplR("DEPTH_ATTACHMENT");
    Renderable Stencil = new FrameAttachmentImplR("STENCIL_ATTACHMENT");
    Renderable DepthStencil = new FrameAttachmentImplR("DEPTH_STENCIL_ATTACHMENT");

    sealed interface Renderable extends FrameAttachment permits FrameAttachmentImplR, FrameColorAttachment {
    }
}
