package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.util.TypeReference;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;

public interface RenderBuffer extends Bindable<BindTarget.Default<RenderBuffer>, RenderBuffer>, Handle {
    BindableMeta<BindTarget.Default<RenderBuffer>, RenderBuffer> Meta = MetaSystem.Graphics.of(RenderBuffer.class, new TypeReference<>() {
    });
    BindTarget.Default<RenderBuffer> Target = new BindTarget.Default<>(
            MetaSystem.Graphics.queryInt("RENDERBUFFER"), "RenderBuffer", Meta);

    enum Format {
        Color(MetaSystem.Graphics.queryInt("COLOR_RENDERABLE")),
        Depth(MetaSystem.Graphics.queryInt("DEPTH_RENDERABLE")),
        Stencil(MetaSystem.Graphics.queryInt("STENCIL_RENDERABLE"));
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
        return Meta.create();
    }
}
