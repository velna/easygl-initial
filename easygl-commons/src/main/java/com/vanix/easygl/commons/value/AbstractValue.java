package com.vanix.easygl.commons.value;

import java.util.LinkedList;
import java.util.List;

public class AbstractValue<I> implements Value<I> {

	protected List<I> interceptors;

	protected List<I> getInterceptors() {
		if (null == interceptors) {
			interceptors = new LinkedList<>();
		}
		return interceptors;
	}

	@Override
	public void addInterceptor(I iterceptor) {
		getInterceptors().add(iterceptor);
	}

	@Override
	public void removeInterceptor(I iterceptor) {
		getInterceptors().remove(iterceptor);
	}

}
