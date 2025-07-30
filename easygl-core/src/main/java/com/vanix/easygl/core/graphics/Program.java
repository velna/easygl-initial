package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Identified;
import com.vanix.easygl.core.graphics.gl.GLC;
import com.vanix.easygl.core.graphics.gl.GlProgram;
import com.vanix.easygl.core.graphics.gl.GlUniformProgram;

public interface Program extends Bindable<BindTarget.Default<Program>, Program>, Handle, Identified<String> {

    BindTarget.Default<Program> Target = new BindTarget.Default<>(
            BindingState.<BindTarget.Default<Program>, Program>ofInt("Program", GLC::glUseProgram));

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
