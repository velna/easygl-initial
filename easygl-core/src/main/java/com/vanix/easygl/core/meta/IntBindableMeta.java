package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.commons.util.IntBiConsumer;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;

public class IntBindableMeta<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends IntHandleMeta<T> implements BindableMeta<E, T> {
    private final IntBiConsumer bind;
    private final IntBiConsumer unbind;
    private final int unbindValue;

    public IntBindableMeta(Function<Object[], T> factory,
                           IntBiConsumer bind,
                           IntBiConsumer unbind,
                           int unbindValue,
                           IntConsumer close,
                           BiFunction<Integer, Object[], T> init) {
        this(factory, bind, unbind, unbindValue, close, init, null, null);
    }

    public IntBindableMeta(Function<Object[], T> factory,
                           IntBiConsumer bind,
                           IntBiConsumer unbind,
                           int unbindValue,
                           IntConsumer close,
                           BiFunction<Integer, Object[], T> init,
                           Consumer<int[]> initArray,
                           Consumer<int[]> closeArray) {
        super(factory, close, init, initArray, closeArray);
        this.bind = bind;
        this.unbind = unbind;
        this.unbindValue = unbindValue;
    }

    @Override
    public BindingState<E, T> newBindingState(String name, E target) {
        if (bind == null || unbind == null) {
            throw new UnsupportedOperationException();
        }
        return BindingState.ofInt(name,
                h -> bind.accept(target.value(), h),
                h -> unbind.accept(target.value(), h),
                unbindValue);
    }

    @Override
    public int unbindValueInt() {
        return unbindValue;
    }
}
