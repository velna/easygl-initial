package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.ClientApp;
import com.vanix.easygl.commons.Identified;

public interface Renderer<A extends ClientApp, C extends RenderContext> extends Closeable, Identified<String> {

    @Override
    default String id() {
        return this.getClass().getSimpleName();
    }

    default void init(A app) throws GraphicsException {

    }

    default void close() {

    }

    default boolean isActive() {
        return true;
    }

    void render(C context) throws GraphicsException;
}
