package com.vanix.easygl.commons.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.collections4.list.GrowthList;
import org.apache.commons.collections4.list.LazyList;

public class ListenerSupport {

	private final List<Collection<?>> list = new ArrayList<>(4);
	private final List<Collection<?>> listeners = LazyList.lazyList(list, LinkedList::new);
	private final List<ListenerKey<?>> listenerKeys = new GrowthList<>();

	public <T extends EventListener> ListenerOperation<T> of(ListenerKey<T> key) {
		return new ListenerOperation<T>() {

			@Override
			public void subscribe(T listener) {
				ListenerSupport.this.add(key, listener);
			}

			@Override
			public void unsubscribe(T listener) {
				ListenerSupport.this.remove(key, listener);
			}

		};
	}

	@SuppressWarnings("unchecked")
	public <T extends EventListener> ListenerKey<T> keyOf(int key) {
		ListenerKey<T> ret;
		if (key >= listenerKeys.size()) {
			ret = ListenerKey.of(key);
			listenerKeys.set(key, ret);
		} else {
			ret = (ListenerKey<T>) listenerKeys.get(key);
			if (null == ret) {
				ret = ListenerKey.of(key);
				listenerKeys.set(key, ret);
			}
		}
		return ret;
	}

	public <T extends EventListener> void add(ListenerKey<T> key, T listener) {
		get(key).add(listener);
	}

	public <T extends EventListener> void remove(ListenerKey<T> key, T listener) {
		get(key).remove(listener);
	}

	@SuppressWarnings("unchecked")
	public <T extends EventListener> void forEach(ListenerKey<T> key, Consumer<? super T> consumer) {
		Collection<T> ls = null;
		if (key.value() < this.list.size() && (ls = (Collection<T>) this.list.get(key.value())) != null) {
			ls.forEach(consumer);
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends EventListener> Collection<T> get(ListenerKey<T> key) {
		return (Collection<T>) listeners.get(key.value());
	}

	public void clear() {
		listeners.clear();
	}
}
