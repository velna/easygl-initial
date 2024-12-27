package com.vanix.easygl.core.graphics;

public abstract class AbstractBindableHandle<T extends Bindable<T>> extends AbstractHandle implements Bindable<T> {

	private final BindingState state;

	protected static final BindingState Null = new BindingState("NULL");

	public AbstractBindableHandle(int handle, BindingState state) {
		super(handle);
		this.state = state;
	}

	protected abstract void bind(int handle);

	@SuppressWarnings("unchecked")
	@Override
	public T assertBinding() throws IllegalStateException {
		state.assertBinding(handle());
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T bind() {
		int handle = handle();
		if (Null == state) {
			bind(handle);
		} else if (handle != state.handle) {
			bind(handle);
			state.handle = handle;
		}
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T unbind() {
		int handle = handle();
		if (Null == state) {
			bind(0);
		} else if (handle == state.handle) {
			bind(state.unbindValue);
			state.handle = state.unbindValue;
		}
		return (T) this;
	}

}
