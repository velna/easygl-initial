package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Identified;

public class BindingState implements Identified<String> {

	private final String id;
	final int unbindValue;
	int handle;

	public BindingState(String id) {
		this(id, 0);
	}

	public BindingState(String id, int unbindValue) {
		this.id = String.format("Binding#%s", id);
		this.handle = this.unbindValue = unbindValue;
	}

	@Override
	public String id() {
		return id;
	}

	public int get() {
		return handle;
	}

	public boolean hasBinding() {
		return handle != unbindValue;
	}

	public void assertBinding(int handle) {
		if (this.handle != handle) {
			throw new IllegalStateException(id + " not bind.");
		}
	}
}
