package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.event.ListenerKey;
import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.core.input.Keyboard;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.input.event.KeyboardEvent;
import com.vanix.easygl.core.input.event.KeyboardListener;
import org.lwjgl.glfw.GLFW;

public class GlKeyboard implements Keyboard {
    private final ListenerSupport listenerSupport = new ListenerSupport();
    private final Window window;

    public GlKeyboard(Window window) {
        this.window = window;
        GLFW.glfwSetKeyCallback(window.handle(), this::onKey);
    }

    private void onKey(long window, int key, int scancode, int action, int mods) {
        KeyboardEvent event = new KeyboardEvent(this, Cache.keyOfValue(key), Cache.actionOfValue(action), scancode, mods);
        ListenerKey<KeyboardListener> lk0 = listenerSupport.keyOf(0);
        listenerSupport.forEach(lk0, l -> l.keyboardOnKey(event));

        ListenerKey<KeyboardListener> lk = listenerSupport.keyOf(key);
        listenerSupport.forEach(lk, l -> l.keyboardOnKey(event));
    }

    @Override
    public Action actionOf(Key input) {
        return Cache.actionOfValue(GLFW.glfwGetKey(window.handle(), input.code()));
    }

    @Override
    public ListenerOperation<KeyboardListener> onKey(Key... keys) {
        return new ListenerOperation<>() {

            @Override
            public void subscribe(KeyboardListener listener) {
                if (null == keys || keys.length == 0) {
                    listenerSupport.add(listenerSupport.keyOf(0), listener);
                } else {
                    for (var key : keys) {
                        listenerSupport.add(listenerSupport.keyOf(key.code()), listener);
                    }
                }
            }

            @Override
            public void unsubscribe(KeyboardListener listener) {
                if (null == keys || keys.length == 0) {
                    listenerSupport.remove(listenerSupport.keyOf(0), listener);
                } else {
                    for (var key : keys) {
                        listenerSupport.remove(listenerSupport.keyOf(key.code()), listener);
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
