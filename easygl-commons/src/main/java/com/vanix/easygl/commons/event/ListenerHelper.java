package com.vanix.easygl.commons.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

public class ListenerHelper {

	private Map<Class<?>, Collection<?>> listenerMap;

	public <T extends EventListener> ListenerOperation<T> of(Class<T> type) {
		return new ListenerOperation<T>() {

			@Override
			public void subscribe(T listener) {
				get(type).add(listener);
			}

			@Override
			public void unsubscribe(T listener) {
				get(type).remove(listener);
			}

		};
	}

	@SuppressWarnings("unchecked")
	public <T extends EventListener> void forEach(Class<T> type, Consumer<? super T> consumer) {
		Collection<T> listeners = null;
		if (null != listenerMap && (listeners = (Collection<T>) listenerMap.get(type)) != null) {
			listeners.forEach(consumer);
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends EventListener> Collection<T> get(Class<?> type) {
		if (null == this.listenerMap) {
			this.listenerMap = new HashMap<>();
		}
		return (Collection<T>) listenerMap.computeIfAbsent(type, key -> new LinkedList<>());
	}

	public void clear() {
		listenerMap.clear();
	}
}
