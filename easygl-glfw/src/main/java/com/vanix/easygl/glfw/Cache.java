package com.vanix.easygl.glfw;

import com.vanix.easygl.core.input.InputDevice;
import com.vanix.easygl.core.input.Keyboard;
import org.eclipse.collections.api.factory.primitive.IntObjectMaps;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

class Cache {

    private static final MutableIntObjectMap<Keyboard.Key> KEY_MAP = IntObjectMaps.mutable.of();
    private static final InputDevice.Action[] ACTIONS = {InputDevice.Action.Release, InputDevice.Action.Press, InputDevice.Action.Repeat};

    static {
        for (var key : Keyboard.PrintableKey.values()) {
            KEY_MAP.put(key.code(), key);
        }
        for (var key : Keyboard.FunctionKey.values()) {
            KEY_MAP.put(key.code(), key);
        }
    }

    static InputDevice.Action actionOfValue(int value){
        return ACTIONS[value];
    }

    static Keyboard.Key keyOfValue(int code){
        return KEY_MAP.get(code);
    }
}
