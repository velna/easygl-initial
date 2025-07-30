package com.vanix.easygl.core.graphics;

public interface BindTarget<E extends BindTarget<E, T>, T extends Bindable<E, T>> {
    int value();

    BindingState<E, T> state();

    record Default<T extends Bindable<Default<T>, T>>(
            BindingState<Default<T>, T> state) implements BindTarget<Default<T>, T> {

        @Override
        public int value() {
            return 0;
        }
    }
}
