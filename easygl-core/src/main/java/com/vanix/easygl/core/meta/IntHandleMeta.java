package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.CloseableArray;
import com.vanix.easygl.core.Handle;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;

public class IntHandleMeta<T extends Handle> extends AbstractHandleMeta<T> {

    private final IntConsumer close;
    private final BiFunction<Integer, Object[], T> init;
    private final Consumer<int[]> initArray;
    private final Consumer<int[]> closeArray;

    public IntHandleMeta(Function<Object[], T> factory,
                         IntConsumer close,
                         BiFunction<Integer, Object[], T> init,
                         Consumer<int[]> initArray,
                         Consumer<int[]> closeArray) {
        super(factory);
        this.close = close;
        this.init = init;
        this.initArray = initArray;
        this.closeArray = closeArray;
    }

    @Override
    public CloseableArray<T> createArray(int n, Object... args) {
        var list = new ArrayList<T>(n);
        int[] handles = new int[n];
        if (initArray != null && init != null) {
            initArray.accept(handles);
            for (var h : handles) {
                list.add(init.apply(h, args));
            }
        } else if (factory != null) {
            for (int i = 0; i < n; i++) {
                var bindable = factory.apply(args);
                handles[i] = bindable.intHandle();
                list.add(bindable);
            }
        } else {
            throw new UnsupportedOperationException();
        }
        return new CloseableArray<>(list, handles, this::close);
    }

    void close(int[] handles) {
        if (closeArray != null) {
            closeArray.accept(handles);
        } else if (close != null) {
            for (var h : handles) {
                close.accept(h);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
