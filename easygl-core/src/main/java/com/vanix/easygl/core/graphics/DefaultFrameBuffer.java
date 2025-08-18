package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.Rectangle;

public interface DefaultFrameBuffer extends BaseFrameBuffer<DefaultFrameBuffer> {

    DefaultFrameBuffer selectDrawBuffer(DefaultFrameBuffer.DrawBuffer drawBuffer);

    DefaultFrameBuffer invalidate(Target<FrameBuffer> target, int x, int y, int width, int height, Invalidatable attachment);

    default DefaultFrameBuffer invalidate(Target<FrameBuffer> target, Rectangle rectangle, Invalidatable attachment) {
        return invalidate(target, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), attachment);
    }

    sealed interface DrawBuffer extends IntEnum permits FrameInnerBuffer.Constants {
        DrawBuffer None = FrameInnerBuffer.Constants.None;
        DrawBuffer FrontLeft = FrameInnerBuffer.Constants.FrontLeft;
        DrawBuffer FrontRight = FrameInnerBuffer.Constants.FrontRight;
        DrawBuffer BackLeft = FrameInnerBuffer.Constants.BackLeft;
        DrawBuffer BackRight = FrameInnerBuffer.Constants.BackRight;
        DrawBuffer Front = FrameInnerBuffer.Constants.Front;
        DrawBuffer Back = FrameInnerBuffer.Constants.Back;
        DrawBuffer Left = FrameInnerBuffer.Constants.Left;
        DrawBuffer Right = FrameInnerBuffer.Constants.Right;
        DrawBuffer FrontAndBack = FrameInnerBuffer.Constants.FrontAndBack;
    }

    sealed interface Invalidatable extends IntEnum permits FrameInnerBuffer.Constants {
        Invalidatable Color = FrameInnerBuffer.Constants.Color;
        Invalidatable Depth = FrameInnerBuffer.Constants.Depth;
        Invalidatable Stencil = FrameInnerBuffer.Constants.Stencil;
        Invalidatable FrontLeft = FrameInnerBuffer.Constants.FrontLeft;
        Invalidatable FrontRight = FrameInnerBuffer.Constants.FrontRight;
        Invalidatable BackLeft = FrameInnerBuffer.Constants.BackLeft;
        Invalidatable BackRight = FrameInnerBuffer.Constants.BackRight;
        Invalidatable Aux0 = FrameInnerBuffer.Constants.Aux0;
        Invalidatable Aux1 = FrameInnerBuffer.Constants.Aux1;
        Invalidatable Aux2 = FrameInnerBuffer.Constants.Aux2;
        Invalidatable Aux3 = FrameInnerBuffer.Constants.Aux3;
    }
}
