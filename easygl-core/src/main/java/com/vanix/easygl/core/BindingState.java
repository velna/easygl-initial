package com.vanix.easygl.core;

import com.vanix.easygl.commons.Identified;

import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public class BindingState<E extends BindTarget<E, T>, T extends Bindable<E, T>> implements Identified<String> {

    private final String id;
    private final LongConsumer unbindFunction;
    private final LongConsumer bindFunction;
    final long unbindValue;
    private long handle;

    private BindingState(String id, LongConsumer bindFunction, LongConsumer unbindFunction, long unbindValue) {
        this.id = String.format("Binding#%s", id);
        this.handle = this.unbindValue = unbindValue;
        this.bindFunction = bindFunction;
        this.unbindFunction = unbindFunction;
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofInt(String id, IntConsumer bindFunction) {
        return ofInt(id, bindFunction, 0);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofInt(String id, IntConsumer bindFunction, int unbindValue) {
        return ofInt(id, bindFunction, bindFunction, unbindValue);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofInt(String id, IntConsumer bindFunction, IntConsumer unbindFunction, int unbindValue) {
        return new BindingState<>(id, h -> bindFunction.accept((int) h), h -> unbindFunction.accept((int) h), unbindValue);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofLong(String id, LongConsumer bindFunction) {
        return ofLong(id, bindFunction, 0);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofLong(String id, LongConsumer bindFunction, long unbindValue) {
        return ofLong(id, bindFunction, bindFunction, unbindValue);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofLong(String id, LongConsumer bindFunction, LongConsumer unbindFunction, long unbindValue) {
        return new BindingState<>(id, bindFunction, unbindFunction, unbindValue);
    }

    @Override
    public String id() {
        return id;
    }

    public T bind(T bindable) {
        long h = bindable.handle();
        bindFunction.accept(h);
        this.handle = h;
        return bindable;
    }

    public void unbind() {
        unbindFunction.accept(unbindValue);
    }

    public boolean hasBinding() {
        return handle != unbindValue;
    }

    public void assertBinding(long handle) {
        if (this.handle != handle) {
            throw new IllegalStateException(id() + " not bind.");
        }
    }

    public long unbindValue() {
        return unbindValue;
    }

}
