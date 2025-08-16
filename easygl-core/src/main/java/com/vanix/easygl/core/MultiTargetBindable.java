package com.vanix.easygl.core;

public interface MultiTargetBindable<E extends BindTarget<E, T>, T extends MultiTargetBindable<E, T>> extends Handle {
    T bind(E target);

    T unbind(E target);

    E target();

    void assertBinding() throws IllegalStateException;
}
