package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.Graphics;
import org.lwjgl.opengl.GL45;

public class GLC extends GL45 {

    protected GLC() {
    }

    public static void checkError() {
        Graphics.ErrorCode errorCode = Graphics.ErrorCode.of(GLC.glGetError());
        if (errorCode.isError()) {
            throw new IllegalStateException(errorCode.toString());
        }
    }
}
