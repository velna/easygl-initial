package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.AbstractBindableHandle;
import com.vanix.easygl.core.graphics.Texture;

public abstract class AbstractTexture<T extends Texture<T>> extends AbstractBindableHandle<T> implements Texture<T> {

	private final Type type;

	private final String id;

	public AbstractTexture(String id, Type type) {
		super(GLC.glGenTextures(), type.state());
		this.id = id;
		this.type = type;
	}

	@Override
	protected void bind(int handle) {
		GLC.glBindTexture(type.value(), handle);
	}

	@Override
	protected void close(int handle) {
		GLC.glDeleteTextures(handle);
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public Type type() {
		return type;
	}

}
