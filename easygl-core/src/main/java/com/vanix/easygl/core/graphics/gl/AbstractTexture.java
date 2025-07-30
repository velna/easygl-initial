package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.AbstractBindable;
import com.vanix.easygl.core.graphics.Texture;

public abstract class AbstractTexture<T extends Texture<T>> extends AbstractBindable<Texture.Type<T>, T> implements Texture<T> {

    private final String id;

    public AbstractTexture(String id, Type<T> type) {
        super(GLC.glGenTextures(), type);
        this.id = id;
    }

    @Override
    protected void close(int handle) {
        GLC.glDeleteTextures(handle);
    }

    @Override
    public String id() {
        return id;
    }

}
