package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

@Support(since = Version.GL30)
public interface RenderBuffer extends Bindable<BindTarget.Default<RenderBuffer>, RenderBuffer>, Handle {
    BindTarget.Default<RenderBuffer> Target = new BindTarget.Default<>(
            MetaSystem.Graphics.queryInt("RENDERBUFFER"), "RenderBuffer", MetaHolder.RenderBuffer);

    @Support(since = Version.GL30)
    RenderBuffer storage(InternalPixelFormat internalFormat, int width, int height);

    @Support(since = Version.GL30)
    RenderBuffer storageMultiSample(int samples, InternalPixelFormat internalFormat, int width, int height);

    static RenderBuffer of() {
        return MetaHolder.RenderBuffer.create();
    }
}
