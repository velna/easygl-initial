package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;

public sealed interface DrawBuffer extends IntEnum permits DrawBuffer.MultiSelectable, DefaultDrawBuffer {
    MultiSelectable None = new MultiSelectableDrawBuffer("NONE");
    MultiSelectable FrontLeft = new MultiSelectableDrawBuffer("FRONT_LEFT");
    MultiSelectable FrontRight = new MultiSelectableDrawBuffer("FRONT_RIGHT");
    MultiSelectable BackLeft = new MultiSelectableDrawBuffer("BACK_LEFT");
    MultiSelectable BackRight = new MultiSelectableDrawBuffer("BACK_RIGHT");
    DrawBuffer Front = new DefaultDrawBuffer("FRONT");
    DrawBuffer Back = new DefaultDrawBuffer("BACK");
    DrawBuffer Left = new DefaultDrawBuffer("LEFT");
    DrawBuffer Right = new DefaultDrawBuffer("RIGHT");
    DrawBuffer FrontAndBack = new DefaultDrawBuffer("FRONT_AND_BACK");

    sealed interface MultiSelectable extends DrawBuffer permits MultiSelectableDrawBuffer, ColorAttachment {

    }

}
