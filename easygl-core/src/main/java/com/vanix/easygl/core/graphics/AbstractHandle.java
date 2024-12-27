package com.vanix.easygl.core.graphics;

public abstract class AbstractHandle implements Handle {

	private int handle;

	public AbstractHandle(int handle) {
		this.handle = handle;
	}

	protected abstract void close(int handle);

	@Override
	public void close() {
		if (handle != 0) {
			close(handle);
			handle = 0;
		}
	}

	@Override
	public int handle() {
		return handle;
	}

}
