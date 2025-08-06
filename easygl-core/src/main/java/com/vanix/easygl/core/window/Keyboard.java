package com.vanix.easygl.core.window;

import com.vanix.easygl.commons.event.ListenerOperation;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.window.event.KeyboardListener;

public interface Keyboard extends InputDevice<Keyboard.Key> {

    ListenerOperation<KeyboardListener> onKey(Key... keys);

    enum Modifier {
        Shift(MetaSystem.Window.queryInt("MOD_SHIFT")),
        Control(MetaSystem.Window.queryInt("MOD_CONTROL")),
        Alt(MetaSystem.Window.queryInt("MOD_ALT")),
        Super(MetaSystem.Window.queryInt("MOD_SUPER")),
        CapsLock(MetaSystem.Window.queryInt("MOD_CAPS_LOCK")),
        NumLock(MetaSystem.Window.queryInt("MOD_NUM_LOCK"));
        private final int value;

        Modifier(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    sealed interface Key extends Input permits PrintableKey, FunctionKey {
        boolean isPrintableKey();

        boolean isFunctionKey();
    }

    enum PrintableKey implements Key {
        SPACE(MetaSystem.Window.queryInt("KEY_SPACE")),
        APOSTROPHE(MetaSystem.Window.queryInt("KEY_APOSTROPHE")),
        COMMA(MetaSystem.Window.queryInt("KEY_COMMA")),
        MINUS(MetaSystem.Window.queryInt("KEY_MINUS")),
        PERIOD(MetaSystem.Window.queryInt("KEY_PERIOD")),
        SLASH(MetaSystem.Window.queryInt("KEY_SLASH")),
        N0(MetaSystem.Window.queryInt("KEY_0")),
        N1(MetaSystem.Window.queryInt("KEY_1")),
        N2(MetaSystem.Window.queryInt("KEY_2")),
        N3(MetaSystem.Window.queryInt("KEY_3")),
        N4(MetaSystem.Window.queryInt("KEY_4")),
        N5(MetaSystem.Window.queryInt("KEY_5")),
        N6(MetaSystem.Window.queryInt("KEY_6")),
        N7(MetaSystem.Window.queryInt("KEY_7")),
        N8(MetaSystem.Window.queryInt("KEY_8")),
        N9(MetaSystem.Window.queryInt("KEY_9")),
        SEMICOLON(MetaSystem.Window.queryInt("KEY_SEMICOLON")),
        EQUAL(MetaSystem.Window.queryInt("KEY_EQUAL")),
        A(MetaSystem.Window.queryInt("KEY_A")),
        B(MetaSystem.Window.queryInt("KEY_B")),
        C(MetaSystem.Window.queryInt("KEY_C")),
        D(MetaSystem.Window.queryInt("KEY_D")),
        E(MetaSystem.Window.queryInt("KEY_E")),
        F(MetaSystem.Window.queryInt("KEY_F")),
        G(MetaSystem.Window.queryInt("KEY_G")),
        H(MetaSystem.Window.queryInt("KEY_H")),
        I(MetaSystem.Window.queryInt("KEY_I")),
        J(MetaSystem.Window.queryInt("KEY_J")),
        K(MetaSystem.Window.queryInt("KEY_K")),
        L(MetaSystem.Window.queryInt("KEY_L")),
        M(MetaSystem.Window.queryInt("KEY_M")),
        N(MetaSystem.Window.queryInt("KEY_N")),
        O(MetaSystem.Window.queryInt("KEY_O")),
        P(MetaSystem.Window.queryInt("KEY_P")),
        Q(MetaSystem.Window.queryInt("KEY_Q")),
        R(MetaSystem.Window.queryInt("KEY_R")),
        S(MetaSystem.Window.queryInt("KEY_S")),
        T(MetaSystem.Window.queryInt("KEY_T")),
        U(MetaSystem.Window.queryInt("KEY_U")),
        V(MetaSystem.Window.queryInt("KEY_V")),
        W(MetaSystem.Window.queryInt("KEY_W")),
        X(MetaSystem.Window.queryInt("KEY_X")),
        Y(MetaSystem.Window.queryInt("KEY_Y")),
        Z(MetaSystem.Window.queryInt("KEY_Z")),
        LEFT_BRACKET(MetaSystem.Window.queryInt("KEY_LEFT_BRACKET")),
        BACKSLASH(MetaSystem.Window.queryInt("KEY_BACKSLASH")),
        RIGHT_BRACKET(MetaSystem.Window.queryInt("KEY_RIGHT_BRACKET")),
        GRAVE_ACCENT(MetaSystem.Window.queryInt("KEY_GRAVE_ACCENT")),
        WORLD_1(MetaSystem.Window.queryInt("KEY_WORLD_1")),
        WORLD_2(MetaSystem.Window.queryInt("KEY_WORLD_2"));

        private final int value;

        PrintableKey(int value) {
            this.value = value;
        }

        @Override
        public int code() {
            return value;
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
        ESCAPE(MetaSystem.Window.queryInt("KEY_ESCAPE")),
        ENTER(MetaSystem.Window.queryInt("KEY_ENTER")),
        TAB(MetaSystem.Window.queryInt("KEY_TAB")),
        BACKSPACE(MetaSystem.Window.queryInt("KEY_BACKSPACE")),
        INSERT(MetaSystem.Window.queryInt("KEY_INSERT")),
        DELETE(MetaSystem.Window.queryInt("KEY_DELETE")),
        RIGHT(MetaSystem.Window.queryInt("KEY_RIGHT")),
        LEFT(MetaSystem.Window.queryInt("KEY_LEFT")),
        DOWN(MetaSystem.Window.queryInt("KEY_DOWN")),
        UP(MetaSystem.Window.queryInt("KEY_UP")),
        PAGE_UP(MetaSystem.Window.queryInt("KEY_PAGE_UP")),
        PAGE_DOWN(MetaSystem.Window.queryInt("KEY_PAGE_DOWN")),
        HOME(MetaSystem.Window.queryInt("KEY_HOME")),
        END(MetaSystem.Window.queryInt("KEY_END")),
        CAPS_LOCK(MetaSystem.Window.queryInt("KEY_CAPS_LOCK")),
        SCROLL_LOCK(MetaSystem.Window.queryInt("KEY_SCROLL_LOCK")),
        NUM_LOCK(MetaSystem.Window.queryInt("KEY_NUM_LOCK")),
        PRINT_SCREEN(MetaSystem.Window.queryInt("KEY_PRINT_SCREEN")),
        PAUSE(MetaSystem.Window.queryInt("KEY_PAUSE")),
        F1(MetaSystem.Window.queryInt("KEY_F1")),
        F2(MetaSystem.Window.queryInt("KEY_F2")),
        F3(MetaSystem.Window.queryInt("KEY_F3")),
        F4(MetaSystem.Window.queryInt("KEY_F4")),
        F5(MetaSystem.Window.queryInt("KEY_F5")),
        F6(MetaSystem.Window.queryInt("KEY_F6")),
        F7(MetaSystem.Window.queryInt("KEY_F7")),
        F8(MetaSystem.Window.queryInt("KEY_F8")),
        F9(MetaSystem.Window.queryInt("KEY_F9")),
        F10(MetaSystem.Window.queryInt("KEY_F10")),
        F11(MetaSystem.Window.queryInt("KEY_F11")),
        F12(MetaSystem.Window.queryInt("KEY_F12")),
        F13(MetaSystem.Window.queryInt("KEY_F13")),
        F14(MetaSystem.Window.queryInt("KEY_F14")),
        F15(MetaSystem.Window.queryInt("KEY_F15")),
        F16(MetaSystem.Window.queryInt("KEY_F16")),
        F17(MetaSystem.Window.queryInt("KEY_F17")),
        F18(MetaSystem.Window.queryInt("KEY_F18")),
        F19(MetaSystem.Window.queryInt("KEY_F19")),
        F20(MetaSystem.Window.queryInt("KEY_F20")),
        F21(MetaSystem.Window.queryInt("KEY_F21")),
        F22(MetaSystem.Window.queryInt("KEY_F22")),
        F23(MetaSystem.Window.queryInt("KEY_F23")),
        F24(MetaSystem.Window.queryInt("KEY_F24")),
        F25(MetaSystem.Window.queryInt("KEY_F25")),
        KP_0(MetaSystem.Window.queryInt("KEY_KP_0")),
        KP_1(MetaSystem.Window.queryInt("KEY_KP_1")),
        KP_2(MetaSystem.Window.queryInt("KEY_KP_2")),
        KP_3(MetaSystem.Window.queryInt("KEY_KP_3")),
        KP_4(MetaSystem.Window.queryInt("KEY_KP_4")),
        KP_5(MetaSystem.Window.queryInt("KEY_KP_5")),
        KP_6(MetaSystem.Window.queryInt("KEY_KP_6")),
        KP_7(MetaSystem.Window.queryInt("KEY_KP_7")),
        KP_8(MetaSystem.Window.queryInt("KEY_KP_8")),
        KP_9(MetaSystem.Window.queryInt("KEY_KP_9")),
        KP_DECIMAL(MetaSystem.Window.queryInt("KEY_KP_DECIMAL")),
        KP_DIVIDE(MetaSystem.Window.queryInt("KEY_KP_DIVIDE")),
        KP_MULTIPLY(MetaSystem.Window.queryInt("KEY_KP_MULTIPLY")),
        KP_SUBTRACT(MetaSystem.Window.queryInt("KEY_KP_SUBTRACT")),
        KP_ADD(MetaSystem.Window.queryInt("KEY_KP_ADD")),
        KP_ENTER(MetaSystem.Window.queryInt("KEY_KP_ENTER")),
        KP_EQUAL(MetaSystem.Window.queryInt("KEY_KP_EQUAL")),
        LEFT_SHIFT(MetaSystem.Window.queryInt("KEY_LEFT_SHIFT")),
        LEFT_CONTROL(MetaSystem.Window.queryInt("KEY_LEFT_CONTROL")),
        LEFT_ALT(MetaSystem.Window.queryInt("KEY_LEFT_ALT")),
        LEFT_SUPER(MetaSystem.Window.queryInt("KEY_LEFT_SUPER")),
        RIGHT_SHIFT(MetaSystem.Window.queryInt("KEY_RIGHT_SHIFT")),
        RIGHT_CONTROL(MetaSystem.Window.queryInt("KEY_RIGHT_CONTROL")),
        RIGHT_ALT(MetaSystem.Window.queryInt("KEY_RIGHT_ALT")),
        RIGHT_SUPER(MetaSystem.Window.queryInt("KEY_RIGHT_SUPER")),
        MENU(MetaSystem.Window.queryInt("KEY_MENU")),
        LAST(MetaSystem.Window.queryInt("KEY_LAST"));
        private final int value;

        FunctionKey(int value) {
            this.value = value;
        }

        @Override
        public int code() {
            return value;
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
