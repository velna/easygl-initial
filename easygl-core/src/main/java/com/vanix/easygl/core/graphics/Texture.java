package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture<T extends Texture<T>> extends Bindable<Texture.Type<T>, T>, Handle {
    BindableMeta<Texture.Unit, Texture.Unit> UnitMeta = MetaSystem.Graphics.of(Texture.Unit.class);

    enum Unit implements Bindable<Unit, Unit>, BindTarget<Unit, Unit> {
        // @formatter:off
        U0,  U1,  U2,  U3,  U4,  U5,  U6,  U7,
        U8,  U9,  U10, U11, U12, U13, U14, U15,
		U16, U17, U18, U19, U20, U21, U22, U23,
		U24, U25, U26, U27, U28, U29, U30, U31;
	    // @formatter:on
        private final BindingState<Unit, Unit> state = UnitMeta.newBindingState("TextureUnit", this);

        private final int value = (int) state.unbindValue() + ordinal();

        @Override
        public long handle() {
            return value;
        }

        @Override
        public int value() {
            return value;
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
        public void assertBinding() throws IllegalStateException {
            state.assertBinding(this.ordinal());
        }

        @Override
        public void close() {

        }
    }

    class Type<T extends Texture<T>> implements BindTarget<Type<T>, T>, Identified<String> {
        //        public static final Type<?> T1D = new Type<>("TEXTURE_1D", GLC.GL_TEXTURE_1D);
        public static final Type<Texture2D> T2D = new Type<>("TEXTURE_2D", MetaSystem.Graphics.queryInt("TEXTURE_2D"), Texture2D.Meta);
        //        public static final Type<?> T3D = new Type<>("TEXTURE_3D", GLC.GL_TEXTURE_3D);
//        public static final Type<?> T1DArray = new Type<>("TEXTURE_1D_ARRAY", GLC.GL_TEXTURE_1D_ARRAY);
//        public static final Type<?> T2DArray = new Type<>("TEXTURE_2D_ARRAY", GLC.GL_TEXTURE_2D_ARRAY);
//        public static final Type<?> Rectangle = new Type<>("TEXTURE_RECTANGLE", GLC.GL_TEXTURE_RECTANGLE);
        public static final Type<TextureCube> CubeMap = new Type<>("TEXTURE_CUBE_MAP", MetaSystem.Graphics.queryInt("TEXTURE_CUBE_MAP"), TextureCube.Meta);
//        public static final Type<?> T2DMultisample = new Type<>("TEXTURE_2D_MULTISAMPLE", GLC.GL_TEXTURE_2D_MULTISAMPLE);
//        public static final Type<?> T2DMultisampleArray = new Type<>("TEXTURE_2D_MULTISAMPLE_ARRAY", GLC.GL_TEXTURE_2D_MULTISAMPLE_ARRAY);

        private final int value;
        private final String id;
        private final BindingState<Type<T>, T> state;

        private Type(String id, int value, BindableMeta<Type<T>, T> meta) {
            this.id = id;
            this.value = value;
            this.state = meta.newBindingState(id, this);
        }

        @Override
        public String id() {
            return id;
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
        Nearest("NEAREST"),
        Linear("LINEAR");

        private final int value;

        MagFilter(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    enum Swizzle {
        Red("RED"),
        Green("GREEN"),
        Blue("BLUE"),
        Alpha("ALPHA"),
        Zero("ZERO"),
        One("ONE");

        private final int value;

        Swizzle(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    enum Wrap {
        ClampToEdge("CLAMP_TO_EDGE"),
        ClampToBorder("CLAMP_TO_BORDER"),
        MirroredRepeat("MIRRORED_REPEAT"),
        Repeat("REPEAT");

        private final int value;

        Wrap(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    enum MinFilter {
        Nearest("NEAREST"),
        Linear("LINEAR"),
        NearestMipmapNearest("NEAREST_MIPMAP_NEAREST"),
        LinearMipmapNearest("LINEAR_MIPMAP_NEAREST"),
        NearestMipmapLinear("NEAREST_MIPMAP_LINEAR"),
        LinearMipmapLinear("LINEAR_MIPMAP_LINEAR");

        private final int value;

        MinFilter(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    default T bind(Texture.Unit unit) {
        unit.bind();
        return this.bind();
    }

    T allocate(int width, int height, PixelFormat format);

    T baseLevel(int value);

    T maxLevel(int value);

    T compareFunc(CompareFunc func);

    T compareModeRefToTexture();

    T compareModeNone();

    T loadBias(float value);

    T minFilter(MinFilter mf);

    T magFilter(MagFilter mf);

    T minLoad(float value);

    T maxLoad(float value);

    T swizzleR(Swizzle swizzle);

    T swizzleG(Swizzle swizzle);

    T swizzleB(Swizzle swizzle);

    T swizzleA(Swizzle swizzle);

    default T swizzle(Swizzle r, Swizzle g, Swizzle b, Swizzle a) {
        return swizzleR(r).swizzleG(g).swizzleB(b).swizzleA(a);
    }

    T wrapS(Wrap wrap);

    T wrapT(Wrap wrap);

    T wrapR(Wrap wrap);

    T generateMipmap();

    static Texture2D of2D() {
        return Texture2D.Meta.create();
    }

    static TextureCube ofCube() {
        return TextureCube.Meta.create();
    }
}
