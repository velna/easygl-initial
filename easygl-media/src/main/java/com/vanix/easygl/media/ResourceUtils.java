package com.vanix.easygl.media;

import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.media.MediaException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceUtils {
    public static String resolve(String resource) {

        URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
        if (null == url) {
            throw new GraphicsException("Can not find image: " + resource);
        }
        try {
            return new File(url.toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new MediaException("Error resolve resource: " + resource, e);
        }
    }
}
