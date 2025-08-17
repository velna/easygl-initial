package com.vanix.easygl.core;

public interface MultiTargetBindable<E extends BindTarget<E, T>, T extends MultiTargetBindable<E, T>> extends Handle {
    @SuppressWarnings("unchecked")
    default T bind(E target) {
        return target.state().bind((T) this, target);
    }

    default void unbind(E target) {
        target.state().unbind(target);
    }

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
