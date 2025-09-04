package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.core.AbstractMultiTargetBindable;
import com.vanix.easygl.core.graphics.*;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.util.function.IntConsumer;

public abstract class AbstractTexture<T extends Texture<T>> extends AbstractMultiTargetBindable<Texture.Target<T>, T> implements Texture<T> {

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

    @Override
    public T borderColor(float red, float green, float blue, float alpha) {
        assertBinding();
        GLX.glTexParameterfv(target().value(), GLX.GL_TEXTURE_BORDER_COLOR, new float[]{red, green, blue, alpha});
        GLX.checkError();
        return self();
    }

    @Override
    public CompareFunction compareFunc() {
        return Cache.CompareFunction.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_COMPARE_FUNC));
    }

    @Override
    public boolean isCompareModeRefToTexture() {
        return GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_COMPARE_MODE) == GLX.GL_COMPARE_REF_TO_TEXTURE;
    }

    @Override
    public float loadBias() {
        return GLX.glGetTextureParameterf(intHandle(), GLX.GL_TEXTURE_LOD_BIAS);
    }

    @Override
    public MinFilter minFilter() {
        return Cache.MinFilter.get(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_MIN_FILTER));
    }

    @Override
    public MagFilter magFilter() {
        return Cache.MagFilter.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_MAG_FILTER));
    }

    @Override
    public float minLoad() {
        return GLX.glGetTextureParameterf(intHandle(), GLX.GL_TEXTURE_MIN_LOD);
    }

    @Override
    public float maxLoad() {
        return GLX.glGetTextureParameterf(intHandle(), GLX.GL_TEXTURE_MAX_LOD);
    }

    @Override
    public Wrap wrapS() {
        return Cache.TextureWrap.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_S));
    }

    @Override
    public Wrap wrapT() {
        return Cache.TextureWrap.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_T));
    }

    @Override
    public Wrap wrapR() {
        return Cache.TextureWrap.valueOf(GLX.glGetTextureParameteri(intHandle(), GLX.GL_TEXTURE_WRAP_R));
    }

    @Override
    public Color borderColor() {
        try (MemoryStack stack = MemoryStack.stackGet()) {
            var buffer = stack.mallocFloat(4);
            GLX.glGetTextureParameterfv(intHandle(), GLX.GL_TEXTURE_BORDER_COLOR, buffer);
            return new Color(buffer.get(0), buffer.get(1), buffer.get(2), buffer.get(3));
        }
    }

    @Override
    public T copyImageSubData(int srcMipMapLevel, int srcX, int srcY, int srcZ, int width, int height, int depth,
                              RenderBuffer dst, int dstX, int dstY, int dstZ) {
        GLX.glCopyImageSubData(intHandle(), target.value(), srcMipMapLevel, srcX, srcY, srcZ,
                dst.intHandle(), dst.target().value(), 0, dstX, dstY, dstZ,
                width, height, depth);
        GLX.checkError();
        return self();
    }

    @Override
    public T copyImageSubData(int srcMipMapLevel, int srcX, int srcY, int srcZ, int width, int height, int depth,
                              Texture<?> dst, int dstMipmapLevel, int dstX, int dstY, int dstZ) {
        GLX.glCopyImageSubData(intHandle(), target.value(), srcMipMapLevel, srcX, srcY, srcZ,
                dst.intHandle(), dst.target().value(), dstMipmapLevel, dstX, dstY, dstZ,
                width, height, depth);
        GLX.checkError();
        return self();
    }
}
