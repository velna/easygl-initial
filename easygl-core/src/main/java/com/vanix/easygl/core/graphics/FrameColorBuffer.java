package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;

public sealed interface FrameColorBuffer extends IntEnum permits FrameColorBuffer.MultiSelectable, FrameColorBufferImpl {
    MultiSelectable None = new FrameColorBufferImplMS("NONE");
    MultiSelectable FrontLeft = new FrameColorBufferImplMS("FRONT_LEFT");
    MultiSelectable FrontRight = new FrameColorBufferImplMS("FRONT_RIGHT");
    MultiSelectable BackLeft = new FrameColorBufferImplMS("BACK_LEFT");
    MultiSelectable BackRight = new FrameColorBufferImplMS("BACK_RIGHT");
    FrameColorBuffer Front = new FrameColorBufferImpl("FRONT");
    FrameColorBuffer Back = new FrameColorBufferImpl("BACK");
    FrameColorBuffer Left = new FrameColorBufferImpl("LEFT");
    FrameColorBuffer Right = new FrameColorBufferImpl("RIGHT");
    FrameColorBuffer FrontAndBack = new FrameColorBufferImpl("FRONT_AND_BACK");

    sealed interface MultiSelectable extends FrameColorBuffer permits FrameColorBufferImplMS, FrameColorAttachment {

    }

}
