package com.vanix.easygl.commons;

public class SimpleIntEnum implements IntEnum {

    private final int value;

    public SimpleIntEnum(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }
}
