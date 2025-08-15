package com.vanix.easygl.core;

import com.vanix.easygl.core.meta.BindableMeta;

public interface BindTarget<E extends BindTarget<E, T>, T extends Handle> {
    int value();

    BindingState<E, T> state();

    @SuppressWarnings("unchecked")
    default T bind(T handle) {
        return state().bind(handle, (E) this);
    }

    @SuppressWarnings("unchecked")
    default T unbind(T handle) {
        state().unbind((E) this);
        return handle;
    }

    class Default<T extends Bindable<Default<T>, T>> implements BindTarget<Default<T>, T> {
        private final int value;
        private final BindingState<Default<T>, T> state;

        public Default(int value, BindingState<Default<T>, T> state) {
            this.value = value;
            this.state = state;
        }

        public Default(int value, String name, BindableMeta<Default<T>, T> meta) {
            this.value = value;
            state = meta.newBindingState(name);
        }

        public Default(BindingState<Default<T>, T> state) {
            this(0, state);
        }

        public Default(String name, BindableMeta<Default<T>, T> meta) {
            this(0, name, meta);
        }

        @Override
        public BindingState<Default<T>, T> state() {
            return state;
        }

        @Override
        public int value() {
            return value;
        }
    }
}
