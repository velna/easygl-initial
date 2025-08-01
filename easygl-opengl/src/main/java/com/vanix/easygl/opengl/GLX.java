package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.*;
import org.lwjgl.opengl.GL45;

public class GLX extends GL45 {
    protected GLX() {
    }

    public static void checkError() {
        Graphics.ErrorCode errorCode = Graphics.ErrorCode.of(GLX.glGetError());
        if (errorCode.isError()) {
            throw new IllegalStateException(errorCode.toString());
        }
    }
}
