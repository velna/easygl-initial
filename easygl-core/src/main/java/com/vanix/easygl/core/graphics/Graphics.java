package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;
import org.joml.Vector4f;

public interface Graphics {

    enum ErrorCode {
        Unknown(-1),
        None(MetaSystem.Graphics.queryInt("NO_ERROR")),
        InvalidEnum(MetaSystem.Graphics.queryInt("INVALID_ENUM")),
        InvalidValue(MetaSystem.Graphics.queryInt("INVALID_VALUE")),
        InvalidOperation(MetaSystem.Graphics.queryInt("INVALID_OPERATION")),
        StackOverflow(MetaSystem.Graphics.queryInt("STACK_OVERFLOW")),
        StackUnderflow(MetaSystem.Graphics.queryInt("STACK_UNDERFLOW")),
        InvalidFramebufferOperation(MetaSystem.Graphics.queryInt("INVALID_FRAMEBUFFER_OPERATION")),
        OutOfMemory(MetaSystem.Graphics.queryInt("OUT_OF_MEMORY"));

        private static final ErrorCode[] Errors = new ErrorCode[ErrorCode.values().length - 2];

        static {
            for (ErrorCode e : ErrorCode.values()) {
                if (e != Unknown && e != None) {
                    Errors[e.value - InvalidEnum.value] = e;
                }
            }
        }

        private final int value;

        private ErrorCode(int value) {
            this.value = value;
        }

        public static ErrorCode of(int error) {
            if (error == None.value) {
                return None;
            }
            int i = error - InvalidEnum.value;
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
        Color(MetaSystem.Graphics.queryInt("COLOR_BUFFER_BIT")), //
        Depth(MetaSystem.Graphics.queryInt("DEPTH_BUFFER_BIT")), //
        Stencil(MetaSystem.Graphics.queryInt("STENCIL_BUFFER_BIT"));

        private final int value;

        private BufferMask(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    default ErrorCode getError() {
        return ErrorCode.of(MetaSystem.Graphics.getError());
    }

    Graphics viewPort(int x, int y, int width, int height);

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

    FrameBuffer defaultFrameBuffer();
}
