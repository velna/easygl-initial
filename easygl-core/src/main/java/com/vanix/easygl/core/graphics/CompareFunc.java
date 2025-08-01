package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

public enum CompareFunc {
	// @formatter:off
	Eq(MetaSystem.Graphics.queryInt("EQUAL")),
	Lt(MetaSystem.Graphics.queryInt("LESS")),
	Gt(MetaSystem.Graphics.queryInt("GREATER")),
	Le(MetaSystem.Graphics.queryInt("LEQUAL")),
	Ge(MetaSystem.Graphics.queryInt("GEQUAL")),
	Ne(MetaSystem.Graphics.queryInt("NOTEQUAL")),
	Always(MetaSystem.Graphics.queryInt("ALWAYS")),
	Never(MetaSystem.Graphics.queryInt("NEVER"));
  //@formatter:on

	private final int value;

	private CompareFunc(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
}
