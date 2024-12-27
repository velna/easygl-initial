package com.vanix.easygl.core.graphics.gl;

public enum GlCompareFunc {
	// @formatter:off
	Eq(GLC.GL_EQUAL),
	Lt(GLC.GL_LESS),
	Gt(GLC.GL_GREATER),
	Le(GLC.GL_LEQUAL),
	Ge(GLC.GL_GEQUAL),
	Ne(GLC.GL_NOTEQUAL),
	Always(GLC.GL_ALWAYS),
	Never(GLC.GL_NEVER);
  //@formatter:on

	private final int value;

	private GlCompareFunc(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
}
