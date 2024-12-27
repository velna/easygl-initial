package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GlProgram;
import com.vanix.easygl.core.graphics.gl.GlUniformProgram;
import com.vanix.easygl.commons.Identified;

public interface Program extends Bindable<Program>, Handle, Identified<String> {
	BindingState State = new BindingState("Program");

	Program attach(Shader shader);

	Program detach(Shader shader);

	Program link() throws GraphicsException;

	static Program of(String id) {
		return new GlProgram(id);
	}

	static <E extends Enum<E>> UniformProgram<E> uniform(String id, Class<E> enumClass) {
		return new GlUniformProgram<>(id, enumClass);
	}
}
