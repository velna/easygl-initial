package com.vanix.easygl.commons.attr;

import com.vanix.easygl.commons.EnumType;

public abstract class AttributesEnum<O> extends EnumType<AttributeType> {
    protected AttributesEnum() {
        super(AttributeType.class);
    }

    protected BooleanAttribute ofBoolean(int key) {
        throw new UnsupportedOperationException();
    }

    protected UpdatableBooleanAttribute<O> ofUpdatableBoolean(int key) {
        throw new UnsupportedOperationException();
    }

    protected IntAttribute ofInt(int key) {
        throw new UnsupportedOperationException();
    }

    protected UpdatableIntAttribute<O> ofUpdatableInt(int key) {
        throw new UnsupportedOperationException();
    }

    protected Attribute<O> of(int key) {
        throw new UnsupportedOperationException();
    }

    protected <T> UpdatableAttribute<O, T> ofUpdatable(int key) {
        throw new UnsupportedOperationException();
    }

}
