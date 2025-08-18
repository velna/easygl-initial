package com.vanix.easygl.core;

public interface MultiTargetBindable<E extends BindTarget<E, T>, T extends MultiTargetBindable<E, T>> extends Handle {
    T bind(E target);

    void unbind(E target);

    E target();

    @SuppressWarnings("unchecked")
    default boolean isBound(E target) {
        return target.state().isBoundTo((T) this);
    }

    @SuppressWarnings("unchecked")
    default void assertBinding() throws IllegalStateException {
        E target = target();
        if (target == null || !target.state().isBoundTo((T) this)) {
            throw new IllegalStateException(this + " not bound.");
        }
    }
}
