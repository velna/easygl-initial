package com.vanix.easygl.core;

import java.util.Objects;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public abstract class AbstractBindable<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends AbstractHandle implements Bindable<E, T> {

    protected final E target;

    protected AbstractBindable(int handle, E target, IntConsumer closeFunction) {
        super(handle, closeFunction);
        this.target = Objects.requireNonNull(target);
    }

    protected AbstractBindable(long handle, E target, LongConsumer closeFunction) {
        super(handle, closeFunction);
        this.target = Objects.requireNonNull(target);
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    @Override
    public E target() {
        return target;
    }

}
