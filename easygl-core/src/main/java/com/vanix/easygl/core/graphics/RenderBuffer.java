package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;
import com.vanix.easygl.core.graphics.gl.GlRenderBuffer;

public interface RenderBuffer extends Bindable<BindTarget.Default<RenderBuffer>, RenderBuffer>, Handle {

    BindTarget.Default<RenderBuffer> Target = new BindTarget.Default<>(
            BindingState.<BindTarget.Default<RenderBuffer>, RenderBuffer>ofInt("RenderBuffer",
                    h -> GLC.glBindRenderbuffer(GLC.GL_RENDERBUFFER, h)));

    enum Format {
        Color(GLC.GL_COLOR_RENDERABLE),
        Depth(GLC.GL_DEPTH_RENDERABLE),
        Stencil(GLC.GL_STENCIL_RENDERABLE);
        private final int value;

        Format(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

    }

    RenderBuffer storage(Format internalFormat, int width, int height);

    RenderBuffer storageMultiSample(int samples, Format internalFormat, int width, int height);

    static RenderBuffer of() {
        return new GlRenderBuffer();
    }
}
