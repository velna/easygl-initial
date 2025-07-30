package com.vanix.easygl.core.graphics.gl;

import org.lwjgl.glfw.GLFW;

import com.vanix.easygl.core.graphics.Keyboard;
import com.vanix.easygl.core.graphics.Window;
import com.vanix.easygl.core.graphics.event.KeyboardListener;
import com.vanix.easygl.commons.event.ListenerKey;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;

public class GlKeyboard implements Keyboard {

	private final ListenerSupport listenerSupport = new ListenerSupport();
	private final Window window;

	public GlKeyboard(Window window) {
		this.window = window;
		GLFW.glfwSetKeyCallback(window.longHandle(), this::onKey);
	}

	private void onKey(long window, int key, int scancode, int action, int mods) {
		ListenerKey<KeyboardListener> lk0 = listenerSupport.keyOf(0);
		listenerSupport.forEach(lk0, l -> l.keyboardOnKey(this, key, scancode, action, mods));

		ListenerKey<KeyboardListener> lk = listenerSupport.keyOf(key);
		listenerSupport.forEach(lk, l -> l.keyboardOnKey(this, key, scancode, action, mods));
	}

	@Override
	public boolean isKeyPressed(int key) {
		return GLFW.glfwGetKey(window.longHandle(), key) == Keyboard.KEY_ACT_PRESS;
	}

	@Override
	public ListenerOperation<KeyboardListener> onKey(int... keys) {
		return new ListenerOperation<>() {

			@Override
			public void subscribe(KeyboardListener listener) {
				if (null == keys || keys.length == 0) {
					listenerSupport.add(listenerSupport.keyOf(0), listener);
				} else {
					for (int key : keys) {
						listenerSupport.add(listenerSupport.keyOf(key), listener);
					}
				}
			}

			@Override
			public void unsubscribe(KeyboardListener listener) {
				if (null == keys || keys.length == 0) {
					listenerSupport.remove(listenerSupport.keyOf(0), listener);
				} else {
					for (int key : keys) {
						listenerSupport.remove(listenerSupport.keyOf(key), listener);
					}
				}
			}

		};
	}

	@Override
	public Window window() {
		return window;
	}

}
