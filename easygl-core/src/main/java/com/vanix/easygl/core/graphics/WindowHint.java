package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GlWindowHint;

import java.nio.ByteBuffer;

public interface WindowHint {

    static BooleanHint ofBoolean(int key) {
        return new GlWindowHint.GlBooleanHint(key);
    }

    static IntHint ofInt(int key) {
        return new GlWindowHint.GlIntHint(key);
    }

    static StringHint ofString(int key) {
        return new GlWindowHint.GlStringHint(key);
    }

    int key();

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
