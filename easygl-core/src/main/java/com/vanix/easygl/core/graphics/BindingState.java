package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Identified;

import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public abstract class BindingState<E extends BindTarget<E, T>, T extends Bindable<E, T>> implements Identified<String> {

    private final String id;

    protected BindingState(String id) {
        this.id = String.format("Binding#%s", id);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofInt(String id, IntConsumer bindFunction) {
        return ofInt(id, bindFunction, bindFunction, 0);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofInt(String id, IntConsumer bindFunction, int unbindValue) {
        return ofInt(id, bindFunction, bindFunction, unbindValue);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofInt(String id, IntConsumer bindFunction, IntConsumer unbindFunction, int unbindValue) {
        return new IntBindState<>(id, bindFunction, unbindFunction, unbindValue);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofLong(String id, LongConsumer bindFunction) {
        return ofLong(id, bindFunction, bindFunction, 0);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofLong(String id, LongConsumer bindFunction, long unbindValue) {
        return ofLong(id, bindFunction, bindFunction, unbindValue);
    }

    public static <E extends BindTarget<E, T>, T extends Bindable<E, T>> BindingState<E, T> ofLong(String id, LongConsumer bindFunction, LongConsumer unbindFunction, long unbindValue) {
        return new LongBindState<>(id, bindFunction, unbindFunction, unbindValue);
    }

    @Override
    public String id() {
        return id;
    }

    public abstract T bind(T bindable);

    public abstract void unbind();

    public abstract boolean hasBinding();

    public abstract void assertBinding(long handle);

    private static class IntBindState<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends BindingState<E, T> {
        private final IntConsumer unbindFunction;
        private final IntConsumer bindFunction;
        final int unbindValue;
        private int handle;

        public IntBindState(String id, IntConsumer bindFunction, IntConsumer unbindFunction, int unbindValue) {
            super(id);
            this.handle = this.unbindValue = unbindValue;
            this.bindFunction = bindFunction;
            this.unbindFunction = unbindFunction;
        }

        public int current() {
            return handle;
        }

        public T bind(T bindable) {
            int h = bindable.handle();
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

    }

    private static class LongBindState<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends BindingState<E, T> {
        private final LongConsumer unbindFunction;
        private final LongConsumer bindFunction;
        final long unbindValue;
        private long handle;

        public LongBindState(String id, LongConsumer bindingFunction) {
            this(id, bindingFunction, 0);
        }

        public LongBindState(String id, LongConsumer bindingFunction, long unbindValue) {
            this(id, bindingFunction, bindingFunction, unbindValue);
        }

        public LongBindState(String id, LongConsumer bindFunction, LongConsumer unbindFunction) {
            this(id, bindFunction, unbindFunction, 0);
        }

        public LongBindState(String id, LongConsumer bindFunction, LongConsumer unbindFunction, long unbindValue) {
            super(id);
            this.handle = this.unbindValue = unbindValue;
            this.bindFunction = bindFunction;
            this.unbindFunction = unbindFunction;
        }

        public T bind(T bindable) {
            long h = bindable.longHandle();
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

    }
}
