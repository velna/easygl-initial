package com.vanix.easygl.core.graphics;

public interface TextureMultiSample extends Texture<TextureMultiSample> {
    TextureMultiSample establish(int samples, InternalPixelFormat pixelFormat, int width, int height, boolean fixedSampleLocations);

    default TextureMultiSample establish(int samples, InternalPixelFormat pixelFormat, int width, int height) {
        return establish(samples, pixelFormat, width, height, true);
    }

    TextureMultiSample establishProxy(int samples, InternalPixelFormat pixelFormat, int width, int height, boolean fixedSampleLocations);

    default TextureMultiSample establishProxy(int samples, InternalPixelFormat pixelFormat, int width, int height) {
        return establishProxy(samples, pixelFormat, width, height, true);
    }

}
