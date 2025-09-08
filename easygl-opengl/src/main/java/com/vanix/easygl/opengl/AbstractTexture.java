package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.graphics.Texture;

import java.util.function.IntConsumer;

public abstract class AbstractTexture<T extends Texture<T>> extends AbstractBindable<Texture.TexTarget<T>, T>
        implements Texture<T> {

    protected AbstractTexture(int handle, Texture.TexTarget<T> target) {
        super(handle, target, (IntConsumer) GLX::glDeleteTextures);
    }

    public int targetValue(boolean proxy) {
        return target.value(proxy);
    }

    @Override
    public T invalidateSub(int level, int xOffset, int yOffset, int zOffset, int width, int height, int depth) {
        GLX.glInvalidateTexSubImage(intHandle(), level, xOffset, yOffset, zOffset, width, height, depth);
        GLX.checkError();
        return self();
    }
}
