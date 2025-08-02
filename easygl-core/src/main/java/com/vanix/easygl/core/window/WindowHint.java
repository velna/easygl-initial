package com.vanix.easygl.core.window;


import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.commons.util.TypeReference;

import java.nio.ByteBuffer;

public interface WindowHint {

    static BooleanHint ofBoolean(int key) {
        return newHint(BooleanHint.class, key);
    }

    static IntHint ofInt(int key) {
        return newHint(IntHint.class, key);
    }

    static StringHint ofString(int key) {
        return newHint(StringHint.class, key);
    }

    int key();

    private static <T extends WindowHint> T newHint(Class<T> type, int key) {
        return MetaSystem.Window.of(type, new TypeReference<>() {
        }, key);
    }

    interface BooleanHint extends WindowHint {
        void set(boolean value);

        default void enable() {
            set(true);
        }

        default void disable() {
            set(false);
        }
    }

    interface IntHint extends WindowHint {
        void set(int value);

        void doNotCare();
    }

    interface StringHint extends WindowHint {
        void set(CharSequence value);

        void set(ByteBuffer value);
    }

}
