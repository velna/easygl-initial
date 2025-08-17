package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum CompareFunction {
    Equal("EQUAL"),
    LessThan("LESS"),
    GreaterThan("GREATER"),
    LessEqual("LEQUAL"),
    GreaterEqual("GEQUAL"),
    NotEqual("NOTEQUAL"),
    Always("ALWAYS"),
    Never("NEVER");

    private final int value;

    private CompareFunction(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
