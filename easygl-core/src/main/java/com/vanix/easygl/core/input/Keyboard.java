package com.vanix.easygl.core.input;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.core.input.event.KeyboardListener;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Keyboard extends InputDevice<Keyboard.Key> {

    ListenerOperation<KeyboardListener> onKey(Key... keys);

    @Override
    default Type type() {
        return Type.Keyboard;
    }

    sealed interface Key extends Input permits PrintableKey, FunctionKey {
        int scancode();

        String keyName();

        boolean isPrintableKey();

        boolean isFunctionKey();
    }

    enum PrintableKey implements Key {
        SPACE(MetaSystem.Window.queryArray("KEY_SPACE")),
        APOSTROPHE(MetaSystem.Window.queryArray("KEY_APOSTROPHE")),
        COMMA(MetaSystem.Window.queryArray("KEY_COMMA")),
        MINUS(MetaSystem.Window.queryArray("KEY_MINUS")),
        PERIOD(MetaSystem.Window.queryArray("KEY_PERIOD")),
        SLASH(MetaSystem.Window.queryArray("KEY_SLASH")),
        N0(MetaSystem.Window.queryArray("KEY_0")),
        N1(MetaSystem.Window.queryArray("KEY_1")),
        N2(MetaSystem.Window.queryArray("KEY_2")),
        N3(MetaSystem.Window.queryArray("KEY_3")),
        N4(MetaSystem.Window.queryArray("KEY_4")),
        N5(MetaSystem.Window.queryArray("KEY_5")),
        N6(MetaSystem.Window.queryArray("KEY_6")),
        N7(MetaSystem.Window.queryArray("KEY_7")),
        N8(MetaSystem.Window.queryArray("KEY_8")),
        N9(MetaSystem.Window.queryArray("KEY_9")),
        SEMICOLON(MetaSystem.Window.queryArray("KEY_SEMICOLON")),
        EQUAL(MetaSystem.Window.queryArray("KEY_EQUAL")),
        A(MetaSystem.Window.queryArray("KEY_A")),
        B(MetaSystem.Window.queryArray("KEY_B")),
        C(MetaSystem.Window.queryArray("KEY_C")),
        D(MetaSystem.Window.queryArray("KEY_D")),
        E(MetaSystem.Window.queryArray("KEY_E")),
        F(MetaSystem.Window.queryArray("KEY_F")),
        G(MetaSystem.Window.queryArray("KEY_G")),
        H(MetaSystem.Window.queryArray("KEY_H")),
        I(MetaSystem.Window.queryArray("KEY_I")),
        J(MetaSystem.Window.queryArray("KEY_J")),
        K(MetaSystem.Window.queryArray("KEY_K")),
        L(MetaSystem.Window.queryArray("KEY_L")),
        M(MetaSystem.Window.queryArray("KEY_M")),
        N(MetaSystem.Window.queryArray("KEY_N")),
        O(MetaSystem.Window.queryArray("KEY_O")),
        P(MetaSystem.Window.queryArray("KEY_P")),
        Q(MetaSystem.Window.queryArray("KEY_Q")),
        R(MetaSystem.Window.queryArray("KEY_R")),
        S(MetaSystem.Window.queryArray("KEY_S")),
        T(MetaSystem.Window.queryArray("KEY_T")),
        U(MetaSystem.Window.queryArray("KEY_U")),
        V(MetaSystem.Window.queryArray("KEY_V")),
        W(MetaSystem.Window.queryArray("KEY_W")),
        X(MetaSystem.Window.queryArray("KEY_X")),
        Y(MetaSystem.Window.queryArray("KEY_Y")),
        Z(MetaSystem.Window.queryArray("KEY_Z")),
        LEFT_BRACKET(MetaSystem.Window.queryArray("KEY_LEFT_BRACKET")),
        BACKSLASH(MetaSystem.Window.queryArray("KEY_BACKSLASH")),
        RIGHT_BRACKET(MetaSystem.Window.queryArray("KEY_RIGHT_BRACKET")),
        GRAVE_ACCENT(MetaSystem.Window.queryArray("KEY_GRAVE_ACCENT")),
        WORLD_1(MetaSystem.Window.queryArray("KEY_WORLD_1")),
        WORLD_2(MetaSystem.Window.queryArray("KEY_WORLD_2"));

        private final int value;
        private final int scancode;
        private final String keyName;

        PrintableKey(Object[] array) {
            this.value = (Integer) array[0];
            this.scancode = (Integer) array[1];
            this.keyName = (String) array[2];
        }

        @Override
        public int code() {
            return value;
        }

        @Override
        public int scancode() {
            return scancode;
        }

        @Override
        public String keyName() {
            return keyName;
        }

        @Override
        public boolean isPrintableKey() {
            return true;
        }

        @Override
        public boolean isFunctionKey() {
            return false;
        }
    }

    enum FunctionKey implements Key {
        ESCAPE(MetaSystem.Window.queryArray("KEY_ESCAPE")),
        ENTER(MetaSystem.Window.queryArray("KEY_ENTER")),
        TAB(MetaSystem.Window.queryArray("KEY_TAB")),
        BACKSPACE(MetaSystem.Window.queryArray("KEY_BACKSPACE")),
        INSERT(MetaSystem.Window.queryArray("KEY_INSERT")),
        DELETE(MetaSystem.Window.queryArray("KEY_DELETE")),
        RIGHT(MetaSystem.Window.queryArray("KEY_RIGHT")),
        LEFT(MetaSystem.Window.queryArray("KEY_LEFT")),
        DOWN(MetaSystem.Window.queryArray("KEY_DOWN")),
        UP(MetaSystem.Window.queryArray("KEY_UP")),
        PAGE_UP(MetaSystem.Window.queryArray("KEY_PAGE_UP")),
        PAGE_DOWN(MetaSystem.Window.queryArray("KEY_PAGE_DOWN")),
        HOME(MetaSystem.Window.queryArray("KEY_HOME")),
        END(MetaSystem.Window.queryArray("KEY_END")),
        CAPS_LOCK(MetaSystem.Window.queryArray("KEY_CAPS_LOCK")),
        SCROLL_LOCK(MetaSystem.Window.queryArray("KEY_SCROLL_LOCK")),
        NUM_LOCK(MetaSystem.Window.queryArray("KEY_NUM_LOCK")),
        PRINT_SCREEN(MetaSystem.Window.queryArray("KEY_PRINT_SCREEN")),
        PAUSE(MetaSystem.Window.queryArray("KEY_PAUSE")),
        F1(MetaSystem.Window.queryArray("KEY_F1")),
        F2(MetaSystem.Window.queryArray("KEY_F2")),
        F3(MetaSystem.Window.queryArray("KEY_F3")),
        F4(MetaSystem.Window.queryArray("KEY_F4")),
        F5(MetaSystem.Window.queryArray("KEY_F5")),
        F6(MetaSystem.Window.queryArray("KEY_F6")),
        F7(MetaSystem.Window.queryArray("KEY_F7")),
        F8(MetaSystem.Window.queryArray("KEY_F8")),
        F9(MetaSystem.Window.queryArray("KEY_F9")),
        F10(MetaSystem.Window.queryArray("KEY_F10")),
        F11(MetaSystem.Window.queryArray("KEY_F11")),
        F12(MetaSystem.Window.queryArray("KEY_F12")),
        F13(MetaSystem.Window.queryArray("KEY_F13")),
        F14(MetaSystem.Window.queryArray("KEY_F14")),
        F15(MetaSystem.Window.queryArray("KEY_F15")),
        F16(MetaSystem.Window.queryArray("KEY_F16")),
        F17(MetaSystem.Window.queryArray("KEY_F17")),
        F18(MetaSystem.Window.queryArray("KEY_F18")),
        F19(MetaSystem.Window.queryArray("KEY_F19")),
        F20(MetaSystem.Window.queryArray("KEY_F20")),
        F21(MetaSystem.Window.queryArray("KEY_F21")),
        F22(MetaSystem.Window.queryArray("KEY_F22")),
        F23(MetaSystem.Window.queryArray("KEY_F23")),
        F24(MetaSystem.Window.queryArray("KEY_F24")),
        F25(MetaSystem.Window.queryArray("KEY_F25")),
        KP_0(MetaSystem.Window.queryArray("KEY_KP_0")),
        KP_1(MetaSystem.Window.queryArray("KEY_KP_1")),
        KP_2(MetaSystem.Window.queryArray("KEY_KP_2")),
        KP_3(MetaSystem.Window.queryArray("KEY_KP_3")),
        KP_4(MetaSystem.Window.queryArray("KEY_KP_4")),
        KP_5(MetaSystem.Window.queryArray("KEY_KP_5")),
        KP_6(MetaSystem.Window.queryArray("KEY_KP_6")),
        KP_7(MetaSystem.Window.queryArray("KEY_KP_7")),
        KP_8(MetaSystem.Window.queryArray("KEY_KP_8")),
        KP_9(MetaSystem.Window.queryArray("KEY_KP_9")),
        KP_DECIMAL(MetaSystem.Window.queryArray("KEY_KP_DECIMAL")),
        KP_DIVIDE(MetaSystem.Window.queryArray("KEY_KP_DIVIDE")),
        KP_MULTIPLY(MetaSystem.Window.queryArray("KEY_KP_MULTIPLY")),
        KP_SUBTRACT(MetaSystem.Window.queryArray("KEY_KP_SUBTRACT")),
        KP_ADD(MetaSystem.Window.queryArray("KEY_KP_ADD")),
        KP_ENTER(MetaSystem.Window.queryArray("KEY_KP_ENTER")),
        KP_EQUAL(MetaSystem.Window.queryArray("KEY_KP_EQUAL")),
        LEFT_SHIFT(MetaSystem.Window.queryArray("KEY_LEFT_SHIFT")),
        LEFT_CONTROL(MetaSystem.Window.queryArray("KEY_LEFT_CONTROL")),
        LEFT_ALT(MetaSystem.Window.queryArray("KEY_LEFT_ALT")),
        LEFT_SUPER(MetaSystem.Window.queryArray("KEY_LEFT_SUPER")),
        RIGHT_SHIFT(MetaSystem.Window.queryArray("KEY_RIGHT_SHIFT")),
        RIGHT_CONTROL(MetaSystem.Window.queryArray("KEY_RIGHT_CONTROL")),
        RIGHT_ALT(MetaSystem.Window.queryArray("KEY_RIGHT_ALT")),
        RIGHT_SUPER(MetaSystem.Window.queryArray("KEY_RIGHT_SUPER")),
        MENU(MetaSystem.Window.queryArray("KEY_MENU")),
        LAST(MetaSystem.Window.queryArray("KEY_LAST"));
        private final int value;
        private final int scancode;
        private final String keyName;

        FunctionKey(Object[] array) {
            this.value = (Integer) array[0];
            this.scancode = (Integer) array[1];
            this.keyName = (String) array[2];
        }

        @Override
        public int code() {
            return value;
        }

        @Override
        public int scancode() {
            return scancode;
        }

        @Override
        public String keyName() {
            return keyName;
        }

        @Override
        public boolean isPrintableKey() {
            return false;
        }

        @Override
        public boolean isFunctionKey() {
            return true;
        }
    }

}
