package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.core.graphics.gl.GLC;
import com.vanix.easygl.core.graphics.gl.GlCompareFunc;
import com.vanix.easygl.core.graphics.gl.GlTexture2D;
import com.vanix.easygl.core.graphics.gl.GlTextureCube;
import org.lwjgl.system.MemoryUtil;

@SuppressWarnings("unchecked")
public interface Texture<T extends Texture<T>> extends Bindable<Texture.Type<T>, T>, Handle, Identified<String> {

    enum Unit implements Bindable<Unit, Unit>, BindTarget<Unit, Unit> {
        // @formatter:off
        U0,  U1,  U2,  U3,  U4,  U5,  U6,  U7,
        U8,  U9,  U10, U11, U12, U13, U14, U15,
		U16, U17, U18, U19, U20, U21, U22, U23,
		U24, U25, U26, U27, U28, U29, U30, U31;
	    // @formatter:on

        private static final BindingState<Unit, Unit> state = BindingState.ofInt("TextureUnit", GLC::glActiveTexture, GLC.GL_TEXTURE0);

        @Override
        public int handle() {
            return this.ordinal() + GLC.GL_TEXTURE0;
        }

        @Override
        public int value() {
            return handle();
        }

        @Override
        public Unit target() {
            return this;
        }

        @Override
        public BindingState<Unit, Unit> state() {
            return state;
        }

        @Override
        public Unit bind() {
            return state.bind(this);
        }

        @Override
        public Unit unbind() {
            state.unbind();
            return this;
        }

        @Override
        public Unit assertBinding() throws IllegalStateException {
            state.assertBinding(this.ordinal());
            return this;
        }

        @Override
        public void close() {

        }
    }

    class Type<T extends Texture<T>> implements BindTarget<Type<T>, T> {
        public static final Type<?> T1D = new Type<>("TEXTURE_1D", GLC.GL_TEXTURE_1D);
        public static final Type<Texture2D> T2D = new Type<>("TEXTURE_2D", GLC.GL_TEXTURE_2D);
        public static final Type<?> T3D = new Type<>("TEXTURE_3D", GLC.GL_TEXTURE_3D);
        public static final Type<?> T1DArray = new Type<>("TEXTURE_1D_ARRAY", GLC.GL_TEXTURE_1D_ARRAY);
        public static final Type<?> T2DArray = new Type<>("TEXTURE_2D_ARRAY", GLC.GL_TEXTURE_2D_ARRAY);
        public static final Type<?> Rectangle = new Type<>("TEXTURE_RECTANGLE", GLC.GL_TEXTURE_RECTANGLE);
        public static final Type<TextureCube> CubeMap = new Type<>("TEXTURE_CUBE_MAP", GLC.GL_TEXTURE_CUBE_MAP);
        public static final Type<?> T2DMultisample = new Type<>("TEXTURE_2D_MULTISAMPLE", GLC.GL_TEXTURE_2D_MULTISAMPLE);
        public static final Type<?> T2DMultisampleArray = new Type<>("TEXTURE_2D_MULTISAMPLE_ARRAY", GLC.GL_TEXTURE_2D_MULTISAMPLE_ARRAY);

        private final int value;
        private final BindingState<Type<T>, T> state;

        private Type(String id, int value) {
            this.value = value;
            state = BindingState.ofInt(id, h -> GLC.glBindTexture(value, h));
        }

        @Override
        public int value() {
            return value;
        }

        @Override
        public BindingState<Type<T>, T> state() {
            return state;
        }
    }

    enum MagFilter {
        Nearest(GLC.GL_NEAREST),
        Linear(GLC.GL_LINEAR);

        private final int value;

        private MagFilter(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    enum Swizzle {
        Red(GLC.GL_RED),
        Green(GLC.GL_GREEN),
        Blue(GLC.GL_BLUE),
        Alpha(GLC.GL_ALPHA),
        Zero(GLC.GL_ZERO),
        One(GLC.GL_ONE);

        private final int value;

        private Swizzle(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    enum Wrap {
        ClampToEdge(GLC.GL_CLAMP_TO_EDGE),
        ClampToBorder(GLC.GL_CLAMP_TO_BORDER),
        MirroredRepeat(GLC.GL_MIRRORED_REPEAT),
        Repeat(GLC.GL_REPEAT);

        private final int value;

        private Wrap(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    enum MinFilter {
        Nearest(GLC.GL_NEAREST),
        Linear(GLC.GL_LINEAR),
        NearestMipmapNearest(GLC.GL_NEAREST_MIPMAP_NEAREST),
        LinearMipmapNearest(GLC.GL_LINEAR_MIPMAP_NEAREST),
        NearestMipmapLinear(GLC.GL_NEAREST_MIPMAP_LINEAR),
        LinearMipmapLinear(GLC.GL_LINEAR_MIPMAP_LINEAR);

        private final int value;

        private MinFilter(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    default T allocate(int width, int height, PixelFormat format) {
        assertBinding();
        GLC.glTexImage2D(target().value(), 0, format.value(), width, height, 0, format.value(), GLC.GL_UNSIGNED_BYTE,
                MemoryUtil.NULL);
        GLC.checkError();
        return (T) this;
    }

    default T baseLevel(int value) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_BASE_LEVEL, value);
        GLC.checkError();
        return (T) this;
    }

    default T maxLevel(int value) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_MAX_LEVEL, value);
        GLC.checkError();
        return (T) this;
    }

    default T compareFunc(GlCompareFunc func) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_COMPARE_FUNC, func.value());
        GLC.checkError();
        return (T) this;
    }

    default T compareModeRefToTexture() {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_COMPARE_MODE, GLC.GL_COMPARE_REF_TO_TEXTURE);
        GLC.checkError();
        return (T) this;
    }

    default T compareModeNone() {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_COMPARE_MODE, GLC.GL_NONE);
        GLC.checkError();
        return (T) this;
    }

    default T loadBias(float value) {
        assertBinding();
        GLC.glTexParameterf(target().value(), GLC.GL_TEXTURE_LOD_BIAS, value);
        GLC.checkError();
        return (T) this;
    }

    default T minFilter(MinFilter mf) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_MIN_FILTER, mf.value());
        GLC.checkError();
        return (T) this;
    }

    default T magFilter(MagFilter mf) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_MAG_FILTER, mf.value());
        GLC.checkError();
        return (T) this;
    }

    default T minLoad(float value) {
        assertBinding();
        GLC.glTexParameterf(target().value(), GLC.GL_TEXTURE_MIN_LOD, value);
        GLC.checkError();
        return (T) this;
    }

    default T maxLoad(float value) {
        assertBinding();
        GLC.glTexParameterf(target().value(), GLC.GL_TEXTURE_MAX_LOD, value);
        GLC.checkError();
        return (T) this;
    }

    default T swizzleR(Swizzle swizzle) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_SWIZZLE_R, swizzle.value());
        GLC.checkError();
        return (T) this;
    }

    default T swizzleG(Swizzle swizzle) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_SWIZZLE_G, swizzle.value());
        GLC.checkError();
        return (T) this;
    }

    default T swizzleB(Swizzle swizzle) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_SWIZZLE_B, swizzle.value());
        GLC.checkError();
        return (T) this;
    }

    default T swizzleA(Swizzle swizzle) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_SWIZZLE_A, swizzle.value());
        GLC.checkError();
        return (T) this;
    }

    default T swizzle(Swizzle r, Swizzle g, Swizzle b, Swizzle a) {
        swizzleR(r);
        swizzleG(g);
        swizzleB(b);
        swizzleA(a);
        return (T) this;
    }

    default T wrapS(Wrap wrap) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_WRAP_S, wrap.value());
        GLC.checkError();
        return (T) this;
    }

    default T wrapT(Wrap wrap) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_WRAP_T, wrap.value());
        GLC.checkError();
        return (T) this;
    }

    default T wrapR(Wrap wrap) {
        assertBinding();
        GLC.glTexParameteri(target().value(), GLC.GL_TEXTURE_WRAP_R, wrap.value());
        GLC.checkError();
        return (T) this;
    }

    default T generateMipmap() {
        assertBinding();
        GLC.glGenerateMipmap(target().value());
        GLC.checkError();
        return (T) this;
    }

    static Texture2D of2D(String id) {
        return new GlTexture2D(id);
    }

    static TextureCube ofCube(String id) {
        return new GlTextureCube(id);
    }
}
