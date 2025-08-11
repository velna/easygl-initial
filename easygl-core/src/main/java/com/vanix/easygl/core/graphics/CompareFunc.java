package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum CompareFunc {
    // @formatter:off
	Eq("EQUAL"),
	Lt("LESS"),
	Gt("GREATER"),
	Le("LEQUAL"),
	Ge("GEQUAL"),
	Ne("NOTEQUAL"),
	Always("ALWAYS"),
	Never("NEVER");
  //@formatter:on

    private final int value;

    private CompareFunc(String id) {
        this.value = MetaSystem.Graphics.queryInt(id);
    }

    public int value() {
        return value;
    }
}
