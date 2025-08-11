package com.vanix.easygl.stb;

import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.meta.AbstractMetaService;
import com.vanix.easygl.core.meta.DefaultMeta;
import com.vanix.easygl.core.meta.ErrorCode;
import com.vanix.easygl.core.meta.SystemName;
import org.lwjgl.stb.STBImage;
import org.lwjgl.stb.STBImageResize;

@SystemName("Media")
public class StbMetaService extends AbstractMetaService {
    public StbMetaService() {
        register(Image.class, new DefaultMeta<>(args -> StbImage.load((String) args[0])));
    }

    @Override
    public int queryInt(String id) {
        return queryStaticIntField(STBImage.class, "STBI_", id)
                .or(() -> queryStaticIntField(STBImageResize.class, "STBIR_", id))
                .orElseThrow();
    }

    @Override
    public ErrorCode getError() {
        return null;
    }

    @Override
    public void close() {

    }
}
