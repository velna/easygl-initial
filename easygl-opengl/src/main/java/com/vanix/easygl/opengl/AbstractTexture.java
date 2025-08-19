package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractMultiTargetBindable;
import com.vanix.easygl.core.graphics.*;
import org.lwjgl.system.MemoryUtil;

import java.util.function.IntConsumer;

public abstract class AbstractTexture<T extends Texture<T>> extends AbstractMultiTargetBindable<Texture.Type<T>, T> implements Texture<T> {

    protected AbstractTexture(int handle) {
        super(handle, (IntConsumer) GLX::glDeleteTextures);
    }

    public AbstractTexture() {
        this(GLX.glGenTextures());
    }

    @Override
    public T allocate(int width, int height, PixelFormat format) {
        assertBinding();
        GLX.glTexImage2D(target().value(), 0, format.value(), width, height, 0, format.value(), GLX.GL_UNSIGNED_BYTE, MemoryUtil.NULL);
        GLX.checkError();
        return self();
    }

    @Override
    public T baseLevel(int value) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_BASE_LEVEL, value);
        GLX.checkError();
        return self();
    }

    @Override
    public T maxLevel(int value) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_MAX_LEVEL, value);
        GLX.checkError();
        return self();
    }

    @Override
    public T compareFunc(CompareFunction func) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_COMPARE_FUNC, func.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T compareModeRefToTexture() {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_COMPARE_MODE, GLX.GL_COMPARE_REF_TO_TEXTURE);
        GLX.checkError();
        return self();
    }

    @Override
    public T compareModeNone() {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_COMPARE_MODE, GLX.GL_NONE);
        GLX.checkError();
        return self();
    }

    @Override
    public T loadBias(float value) {
        assertBinding();
        GLX.glTexParameterf(target().value(), GLX.GL_TEXTURE_LOD_BIAS, value);
        GLX.checkError();
        return self();
    }

    @Override
    public T minFilter(MinFilter mf) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_MIN_FILTER, mf.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T magFilter(MagFilter mf) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_MAG_FILTER, mf.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T minLoad(float value) {
        assertBinding();
        GLX.glTexParameterf(target().value(), GLX.GL_TEXTURE_MIN_LOD, value);
        GLX.checkError();
        return self();
    }

    @Override
    public T maxLoad(float value) {
        assertBinding();
        GLX.glTexParameterf(target().value(), GLX.GL_TEXTURE_MAX_LOD, value);
        GLX.checkError();
        return self();
    }

    @Override
    public T swizzleR(Swizzle swizzle) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_SWIZZLE_R, swizzle.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T swizzleG(Swizzle swizzle) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_SWIZZLE_G, swizzle.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T swizzleB(Swizzle swizzle) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_SWIZZLE_B, swizzle.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T swizzleA(Swizzle swizzle) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_SWIZZLE_A, swizzle.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T wrapS(Wrap wrap) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_WRAP_S, wrap.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T wrapT(Wrap wrap) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_WRAP_T, wrap.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T wrapR(Wrap wrap) {
        assertBinding();
        GLX.glTexParameteri(target().value(), GLX.GL_TEXTURE_WRAP_R, wrap.value());
        GLX.checkError();
        return self();
    }

    @Override
    public T generateMipmap() {
        assertBinding();
        GLX.glGenerateMipmap(target().value());
        GLX.checkError();
        return self();
    }

}
