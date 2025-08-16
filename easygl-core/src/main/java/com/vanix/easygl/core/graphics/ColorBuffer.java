package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;

public sealed interface ColorBuffer extends IntEnum permits ColorBuffer.MultiSelectable, ColorBufferImpl {
    MultiSelectable None = new ColorBufferImplMS("NONE");
    MultiSelectable FrontLeft = new ColorBufferImplMS("FRONT_LEFT");
    MultiSelectable FrontRight = new ColorBufferImplMS("FRONT_RIGHT");
    MultiSelectable BackLeft = new ColorBufferImplMS("BACK_LEFT");
    MultiSelectable BackRight = new ColorBufferImplMS("BACK_RIGHT");
    ColorBuffer Front = new ColorBufferImpl("FRONT");
    ColorBuffer Back = new ColorBufferImpl("BACK");
    ColorBuffer Left = new ColorBufferImpl("LEFT");
    ColorBuffer Right = new ColorBufferImpl("RIGHT");
    ColorBuffer FrontAndBack = new ColorBufferImpl("FRONT_AND_BACK");

    sealed interface MultiSelectable extends ColorBuffer permits ColorBufferImplMS, ColorAttachment {

    }

}
