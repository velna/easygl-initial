package com.vanix.easygl.core.graphics;

import org.lwjgl.system.MemoryStack;

import java.io.Closeable;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;

public record Image(PixelFormat format, int width, int height, ByteBuffer data) implements Closeable {

    @Override
    public void close() {
        stbi_image_free(data);
    }

    public static Image load(String resourceFile) throws GraphicsException {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer tWidth = stack.mallocInt(1);
            IntBuffer tHeight = stack.mallocInt(1);
            IntBuffer tChannels = stack.mallocInt(1);
            try {
                URL url = Image.class.getClassLoader().getResource(resourceFile);
                if (null == url) {
                    throw new GraphicsException("Can not find image: " + resourceFile);
                }
                String textureFile = new File(url.toURI()).getAbsolutePath();
                stbi_set_flip_vertically_on_load(true);
                ByteBuffer data = stbi_load(stack.UTF8(textureFile), tWidth, tHeight, tChannels, 0);
                if (null != data) {
                    return new Image(PixelFormat.ofChannels(tChannels.get()), tWidth.get(), tHeight.get(), data);
                } else {
                    throw new GraphicsException("Image load failed: " + resourceFile);
                }
            } catch (URISyntaxException e) {
                throw new GraphicsException("Invalid image file: " + resourceFile, e);
            }
        }
    }
}
