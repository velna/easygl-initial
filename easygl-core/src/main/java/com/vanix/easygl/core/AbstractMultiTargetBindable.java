package com.vanix.easygl.core;

import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public class AbstractMultiTargetBindable<E extends BindTarget<E, T>, T extends MultiTargetBindable<E, T>>
        extends AbstractHandle
        implements MultiTargetBindable<E, T> {

    protected E target;

    public AbstractMultiTargetBindable(int handle, IntConsumer closeFunction) {
        super(handle, closeFunction);
    }

    public AbstractMultiTargetBindable(long handle, LongConsumer closeFunction) {
        super(handle, closeFunction);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T bind(E target) {
        this.target = target;
        return target.bind((T) this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T unbind(E target) {
        this.target = null;
        return target.bind((T) this);
    }

    @Override
    public E target() {
        return target;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T assertBinding() {
        return (T) this;
    }
}
