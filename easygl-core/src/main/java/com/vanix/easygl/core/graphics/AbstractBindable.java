package com.vanix.easygl.core.graphics;

import java.util.Objects;

public abstract class AbstractBindable<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends AbstractHandle implements Bindable<E, T> {

    protected final E target;

    protected AbstractBindable(int handle, E target) {
        super(handle);
        this.target = Objects.requireNonNull(target);
    }

    protected AbstractBindable(long handle, E target) {
        super(handle);
        this.target = Objects.requireNonNull(target);
    }

    @Override
    public E target() {
        return target;
    }

    protected int targetValue() {
        return target.value();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T assertBinding() throws IllegalStateException {
        target.state().assertBinding(handle());
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T bind() {
        return target.state().bind((T) this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T unbind() {
        target.state().unbind();
        return (T) this;
    }

}
