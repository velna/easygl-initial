package com.vanix.easygl.stb;

import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.PixelFormat;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.media.SimpleImage;
import org.lwjgl.system.MemoryStack;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

public class StbImage {
    public static Image load(String resourceFile) {
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
                    return new SimpleImage(PixelFormat.ofChannels(tChannels.get()), tWidth.get(), tHeight.get(), data);
                } else {
                    throw new GraphicsException("Image load failed: " + resourceFile);
                }
            } catch (URISyntaxException e) {
                throw new GraphicsException("Invalid image file: " + resourceFile, e);
            }
        }
    }
}
