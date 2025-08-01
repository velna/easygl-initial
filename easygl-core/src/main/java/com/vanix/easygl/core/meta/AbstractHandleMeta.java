package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.CloseableArray;
import com.vanix.easygl.core.Handle;

import java.util.function.Function;

public class AbstractHandleMeta<T extends Handle> implements HandleMeta<T> {
    protected final Function<Object[], T> factory;

    public AbstractHandleMeta(Function<Object[], T> factory) {
        this.factory = factory;
    }

    @Override
    public T create(Object... args) {
        if (factory == null) {
            throw new UnsupportedOperationException();
        }
        return factory.apply(args);
    }

    @Override
    public CloseableArray<T> createArray(int n, Object... args) {
        throw new UnsupportedOperationException();
    }

}
