package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.core.*;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import lombok.ToString;
import org.joml.Vector3i;
import org.joml.primitives.AABBi;

public interface Texture<T extends Texture<T>> extends MultiTargetBindable<Texture.Target<T>, T>, Handle {

    enum Unit implements Bindable<Unit, Unit>, BindTarget<Unit, Unit> {
        // @formatter:off
        U0,  U1,  U2,  U3,  U4,  U5,  U6,  U7,
        U8,  U9,  U10, U11, U12, U13, U14, U15,
		U16, U17, U18, U19, U20, U21, U22, U23,
		U24, U25, U26, U27, U28, U29, U30, U31;
	    // @formatter:on
        private final BindingState<Unit, Unit> state = MetaHolder.TextureUnit.newBindingState("TextureUnit");

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
        public void close() {

        }
    }

    @ToString
    class Target<T extends Texture<T>> implements BindTarget<Target<T>, T> {
        //        public static final Type<?> T1D = new Type<>("TEXTURE_1D", GLC.GL_TEXTURE_1D);
        public static final Target<Texture2D> T2D = new Target<>("TEXTURE_2D", MetaHolder.Texture2D);
        //        public static final Type<?> T3D = new Type<>("TEXTURE_3D", GLC.GL_TEXTURE_3D);
//        public static final Type<?> T1DArray = new Type<>("TEXTURE_1D_ARRAY", GLC.GL_TEXTURE_1D_ARRAY);
//        public static final Type<?> T2DArray = new Type<>("TEXTURE_2D_ARRAY", GLC.GL_TEXTURE_2D_ARRAY);
//        public static final Type<?> Rectangle = new Type<>("TEXTURE_RECTANGLE", GLC.GL_TEXTURE_RECTANGLE);
        public static final Target<TextureCube> CubeMap = new Target<>("TEXTURE_CUBE_MAP", MetaHolder.TextureCube);
        public static final Target<TextureMultiSample> T2DMultiSample = new Target<>("TEXTURE_2D_MULTISAMPLE", MetaHolder.TextureMultiSample);
//        public static final Type<?> T2DMultisampleArray = new Type<>("TEXTURE_2D_MULTISAMPLE_ARRAY", GLC.GL_TEXTURE_2D_MULTISAMPLE_ARRAY);

        private final int value;
        private final BindingState<Target<T>, T> state;

        private Target(String id, BindableMeta<Target<T>, T> meta) {
            this.value = MetaSystem.Graphics.queryInt(id);
            this.state = meta.newBindingState(id);
        }

        @Override
        public int value() {
            return value;
        }

        @Override
        public BindingState<Target<T>, T> state() {
            return state;
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

    default T bind(Target<T> target, Texture.Unit unit) {
        unit.bind();
        return this.bind(target);
    }

    T allocate(int width, int height, PixelFormat format);

    T baseLevel(int value);

    T maxLevel(int value);

    T compareFunc(CompareFunction func);

    T compareModeRefToTexture();

    T compareModeNone();

    T loadBias(float value);

    T minFilter(MinFilter mf);

    T magFilter(MagFilter mf);

    T minLoad(float value);

    T maxLoad(float value);

    @Support(since = Version.GL33)
    T swizzleR(Swizzle swizzle);

    @Support(since = Version.GL33)
    T swizzleG(Swizzle swizzle);

    @Support(since = Version.GL33)
    T swizzleB(Swizzle swizzle);

    @Support(since = Version.GL33)
    T swizzleA(Swizzle swizzle);

    default T swizzle(Swizzle r, Swizzle g, Swizzle b, Swizzle a) {
        return swizzleR(r).swizzleG(g).swizzleB(b).swizzleA(a);
    }

    T wrapS(Wrap wrap);

    T wrapT(Wrap wrap);

    T wrapR(Wrap wrap);

    T generateMipmap();

    T borderColor(float red, float green, float blue, float alpha);

    default T borderColor(Color color) {
        return borderColor(color.red(), color.green(), color.blue(), color.alpha());
    }

    //region CopyImageSubData
    @Support(since = Version.GL43)
    T copyImageSubData(int srcMipMapLevel, int srcX, int srcY, int srcZ, int width, int height, int depth,
                       RenderBuffer dst, int dstX, int dstY, int dstZ);

    @Support(since = Version.GL43)
    T copyImageSubData(int srcMipMapLevel, int srcX, int srcY, int srcZ, int width, int height, int depth,
                       Texture<?> dst, int dstMipmapLevel, int dstX, int dstY, int dstZ);

    @Support(since = Version.GL43)
    default T copyImageSubData(int srcMipMapLevel, Vector3i srcXyz, Vector3i size, RenderBuffer dst, Vector3i dstXyz) {
        return copyImageSubData(srcMipMapLevel, srcXyz.x, srcXyz.y, srcXyz.z, size.x, size.y, size.z,
                dst, dstXyz.x, dstXyz.y, dstXyz.z);
    }

    @Support(since = Version.GL43)
    default T copyImageSubData(int srcMipMapLevel, Vector3i srcXyz, Vector3i size, Texture<?> dst, int dstMipmapLevel, Vector3i dstXyz) {
        return copyImageSubData(srcMipMapLevel, srcXyz.x, srcXyz.y, srcXyz.z, size.x, size.y, size.z,
                dst, dstMipmapLevel, dstXyz.x, dstXyz.y, dstXyz.z);
    }

    @Support(since = Version.GL43)
    default T copyImageSubData(int srcMipMapLevel, AABBi src, RenderBuffer dst, Vector3i dstXyz) {
        return copyImageSubData(srcMipMapLevel, src.minX, src.minY, src.minZ, src.lengthX(), src.lengthY(), src.lengthZ(),
                dst, dstXyz.x, dstXyz.y, dstXyz.z);
    }

    @Support(since = Version.GL43)
    default T copyImageSubData(int srcMipMapLevel, AABBi src, Texture<?> dst, int dstMipmapLevel, Vector3i dstXyz) {
        return copyImageSubData(srcMipMapLevel, src.minX, src.minY, src.minZ, src.lengthX(), src.lengthY(), src.lengthZ(),
                dst, dstMipmapLevel, dstXyz.x, dstXyz.y, dstXyz.z);
    }
    //endregion

    static Texture2D of2D() {
        return MetaHolder.Texture2D.create();
    }

    static TextureCube ofCube() {
        return MetaHolder.TextureCube.create();
    }

    static TextureMultiSample ofMultiSample() {
        return MetaHolder.TextureMultiSample.create();
    }
}
