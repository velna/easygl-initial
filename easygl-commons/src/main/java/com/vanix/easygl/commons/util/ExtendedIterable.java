package com.vanix.easygl.commons.util;

import java.util.concurrent.atomic.AtomicInteger;

public interface ExtendedIterable<T> extends Iterable<T> {
    default void forEach(EachAction<T> action) {
        AtomicInteger index = new AtomicInteger();
        forEach(e -> action.accept(e, index.getAndIncrement()));
    }

    interface EachAction<T> {
        void accept(T e, int i);
    }
}
