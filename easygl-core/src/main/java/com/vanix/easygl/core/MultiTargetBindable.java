package com.vanix.easygl.core;

public interface MultiTargetBindable<E extends BindTarget<E, T>, T extends MultiTargetBindable<E, T>> extends Handle {

    @SuppressWarnings("unchecked")
    default T bind(E target) {
        return target.bind((T) this);
    }

    @SuppressWarnings("unchecked")
    default T unbind(E target) {
        return target.unbind((T) this);
    }
}
