package com.vanix.easygl.core;

import com.vanix.easygl.commons.util.ExtendedIterable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CloseableArray<T extends Closeable> implements Closeable, ExtendedIterable<T> {
    private final Consumer<int[]> closeFunction;
    protected final int[] handles;
    private final List<T> list;

    public CloseableArray(List<T> list, int[] handles, Consumer<int[]> closeFunction) {
        this.list = list;
        this.handles = handles;
        this.closeFunction = closeFunction;
    }

    public CloseableArray(List<T> list) {
        this(list, null, null);
    }

    public static <T extends Closeable> CloseableArray<T> of(int n, Supplier<T> factory) {
        List<T> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(factory.get());
        }
        return new CloseableArray<>(list);
    }

    public static <T extends Handle> CloseableArray<T> initInit(List<T> list, int[] handles, Consumer<int[]> closeFunction) {
        if (handles != null && list.size() != handles.length) {
            throw new IllegalArgumentException();
        }
        int[] handlesArray;
        if (handles != null) {
            handlesArray = handles.clone();
        } else {
            handlesArray = new int[list.size()];
            int i = 0;
            for (var e : list) {
                handlesArray[i++] = e.intHandle();
            }
        }
        return new CloseableArray<>(new ArrayList<>(list), handlesArray, closeFunction);
    }

    public T getFirst() {
        return list.getFirst();
    }

    public T getLast() {
        return list.getLast();
    }

    public T get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void close() {
        if (closeFunction != null && handles != null) {
            closeFunction.accept(handles);
        } else {
            list.forEach(Closeable::close);
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(list.iterator());
    }
}
