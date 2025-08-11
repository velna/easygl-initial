package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.window.Window;
import org.joml.Vector4f;

import java.util.ServiceLoader;

public interface Graphics extends Closeable {

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

    static Graphics of() {
        return ServiceLoader.load(Graphics.class).findFirst().orElseThrow();
    }

    static Graphics of(Window window) {
        window.bind();
        return of().viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());
    }
}
