package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.BindingState;

public interface BindableMeta<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends HandleMeta<T> {
    BindingState<E, T> newBindingState(String name, E target);

    default long unbindValueLong() {
        throw new UnsupportedOperationException();
    }

    default int unbindValueInt() {
        throw new UnsupportedOperationException();
    }
}
