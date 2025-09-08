package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture3D extends Texture<Texture3D>,
        TextureOps.MakeView,
        TextureOps.Parameters<Texture3D>,
        TextureOps.CopyImageSubData<Texture3D>,
        TextureOps.GenerateMipmap<Texture3D>,
        TextureOps.Load3D<Texture3D>,
        TextureOps.Load3DSub<Texture3D>,
        TextureOps.Copy3DSub<Texture3D>,
        TextureOps.Load3DCompressed<Texture3D>,
        TextureOps.Load3DCompressedSub<Texture3D>,
        TextureOps.SetStorage3D<Texture3D> {
    Texture.TexTarget<Texture3D> Target = new Texture.TexTarget<>(MetaSystem.Graphics.queryInt("TEXTURE_3D"), "Texture3D", MetaHolder.Texture3D);

    static Texture3D of() {
        return MetaHolder.Texture3D.create();
    }
}
