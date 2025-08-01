package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.util.LongBiConsumer;

import java.util.Objects;
import java.util.function.Function;

public class LongBindableMeta<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends AbstractHandleMeta<T> implements BindableMeta<E, T> {
    private final LongBiConsumer bind;
    private final LongBiConsumer unbind;
    private final long unbindValue;

    public LongBindableMeta(Function<Object[], T> factory,
                            LongBiConsumer bind,
                            LongBiConsumer unbind,
                            long unbindValue) {
        super(factory);
        this.bind = Objects.requireNonNull(bind);
        this.unbind = Objects.requireNonNull(unbind);
        this.unbindValue = unbindValue;
    }

    @Override
    public BindingState<E, T> newBindingState(String name, E target) {
        return BindingState.ofLong(name,
                h -> bind.accept(target.value(), h),
                h -> unbind.accept(target.value(), h),
                unbindValue);
    }

    @Override
    public long unbindValueLong() {
        return unbindValue;
    }
}
