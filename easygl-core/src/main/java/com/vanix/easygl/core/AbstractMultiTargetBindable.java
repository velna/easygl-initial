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

    @Override
    public E target() {
        return target;
    }

}
