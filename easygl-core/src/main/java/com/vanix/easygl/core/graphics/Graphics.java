package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;
import com.vanix.easygl.core.graphics.feature.Blend;
import com.vanix.easygl.core.graphics.feature.CullFace;
import com.vanix.easygl.core.graphics.feature.Depth;
import org.joml.Vector4f;

public interface Graphics {

    enum ErrorCode {
        Unknown(-1),
        None(GLC.GL_NO_ERROR),
        InvalidEnum(GLC.GL_INVALID_ENUM),
        InvalidValue(GLC.GL_INVALID_VALUE),
        InvalidOperation(GLC.GL_INVALID_OPERATION),
        StackOverflow(GLC.GL_STACK_OVERFLOW),
        StackUnderflow(GLC.GL_STACK_UNDERFLOW),
        InvalidFramebufferOperation(GLC.GL_INVALID_FRAMEBUFFER_OPERATION),
        OutOfMemory(GLC.GL_OUT_OF_MEMORY);

        private static final ErrorCode[] Errors = new ErrorCode[ErrorCode.values().length - 2];

        static {
            for (ErrorCode e : ErrorCode.values()) {
                if (e != Unknown && e != None) {
                    Errors[e.value - GLC.GL_INVALID_ENUM] = e;
                }
            }
        }

        private final int value;

        private ErrorCode(int value) {
            this.value = value;
        }

        public static ErrorCode of(int error) {
            if (error == GLC.GL_NO_ERROR) {
                return None;
            }
            int i = error - GLC.GL_INVALID_ENUM;
            return i >= Errors.length || i < 0 ? Unknown : Errors[i];
        }

        public boolean isError() {
            return this != None;
        }

        public int value() {
            return value;
        }
    }

    enum BufferMask {
        Color(GLC.GL_COLOR_BUFFER_BIT), //
        Depth(GLC.GL_DEPTH_BUFFER_BIT), //
        Stencil(GLC.GL_STENCIL_BUFFER_BIT);

        private final int value;

        private BufferMask(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    default ErrorCode getError() {
        return ErrorCode.of(GLC.glGetError());
    }

    Graphics viewPort(int x, int y, int width, int height);

    default void drawTrianglesElements(int count, int offset) {
        GLC.glDrawElements(GLC.GL_TRIANGLES, count, GLC.GL_UNSIGNED_INT, offset);
    }

    default void drawTrianglesArray(int first, int count) {
        GLC.glDrawArrays(GLC.GL_TRIANGLES, first, count);
    }

    Depth depth();

    CullFace cullFace();

    Blend blend();

    Graphics clear(BufferMask mask);

    Graphics clear(BufferMask mask1, BufferMask mask2);

    Graphics clear(BufferMask mask, BufferMask mask2, BufferMask mask3);

    Graphics clearColor(float red, float blue, float green, float alpha);

    default Graphics clearColor(Vector4f color) {
        return clearColor(color.x, color.y, color.z, color.w);
    }

    Graphics polygonMode(PolygonFace face, PolygonMode mode);
}
