package com.vanix.easygl.opengl;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.graphics.Texture.Unit;

import java.nio.FloatBuffer;
import java.util.EnumMap;

public class GlUniformProgram<E extends Enum<E>> extends AbstractProgram<UniformProgram<E>> implements UniformProgram<E> {

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static final BindTarget.Default<?> Target = new BindTarget.Default(BindingState.ofInt("Program", GLX::glUseProgram));
    private final EnumMap<E, Integer> uniforms;

    @SuppressWarnings("unchecked")
    protected GlUniformProgram(int handle, Object... args) {
        super(handle, (String) args[0], (BindTarget.Default<UniformProgram<E>>) Target);
        uniforms = new EnumMap<>((Class<E>) args[1]);
    }

    protected GlUniformProgram(Object... args) {
        this(GLX.glCreateProgram(), args);
    }

    private int uniform(E key) throws GraphicsException {
        Integer uniform = uniforms.computeIfAbsent(key, _1 -> {
            int id = GLX.glGetUniformLocation(intHandle(), key.name());
            return id == -1 ? null : id;
        });
        if (uniform == null) {
            throw new GraphicsException("Can not find uniform for name " + key.name());
        }
        return uniform;
    }

    @Override
    public GlUniformProgram<E> bind() {
        return (GlUniformProgram<E>) super.bind();
    }

    @Override
    public UniformProgram<E> set(E key, boolean value) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLX.glUniform1i(id, value ? GLX.GL_TRUE : GLX.GL_FALSE);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram<E> set(E key, int value) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLX.glUniform1i(id, value);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram<E> set(E key, float value) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLX.glUniform1f(id, value);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram<E> set(E key, float[] value) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLX.glUniform1fv(id, value);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram<E> set(E key, FloatBuffer buffer) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLX.glUniformMatrix4fv(id, true, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram<E> set(E key, Unit unit, Texture<?> texture) throws GraphicsException {
        unit.assertBinding();
        texture.assertBinding();
        set(key, unit.ordinal());
        return this;
    }

}
