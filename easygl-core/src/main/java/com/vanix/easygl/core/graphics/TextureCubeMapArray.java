package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public interface TextureCubeMapArray extends Texture<TextureCubeMapArray>,
        TextureOps.MakeView,
        TextureOps.Parameters<TextureCubeMapArray>,
        TextureOps.CopyImageSubData<TextureCubeMapArray>,
        TextureOps.GenerateMipmap<TextureCubeMapArray>,
        TextureOps.Copy3DSub<TextureCubeMapArray>,
        TextureOps.Load3DCompressedSub<TextureCubeMapArray>,
        TextureOps.SetStorage3D<TextureCubeMapArray> {
    Texture.TexTarget<TextureCubeMapArray> Target = new Texture.TexTarget<>(
            MetaSystem.Graphics.queryInt("TEXTURE_CUBE_MAP_ARRAY"),
            "TextureCubeMapArray",
            MetaHolder.TextureCubeMapArray);

    static TextureCubeMapArray of() {
        return MetaHolder.TextureCubeMapArray.create();
    }
}
