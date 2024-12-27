package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.Texture;
import com.vanix.easygl.core.graphics.Texture.Unit;
import com.vanix.easygl.core.graphics.UniformProgram;

import java.nio.FloatBuffer;
import java.util.EnumMap;

public class GlUniformProgram<E extends Enum<E>> extends GlProgram implements UniformProgram<E> {

    private final EnumMap<E, Integer> uniforms;

    public GlUniformProgram(String id, Class<E> enumClass) {
        super(id);
        uniforms = new EnumMap<>(enumClass);
    }

    private int uniform(E key) throws GraphicsException {
        Integer uniform = uniforms.computeIfAbsent(key, _1 -> {
            int id = GLC.glGetUniformLocation(handle(), key.name());
            return id == -1 ? null : id;
        });
        if (uniform == null) {
            throw new GraphicsException("Can not find uniform for name " + key.name());
        }
        return uniform;
    }

    @Override
    @SuppressWarnings("unchecked")
    public GlUniformProgram<E> bind() {
        return (GlUniformProgram<E>) super.bind();
    }

    @Override
    public UniformProgram<E> set(E key, boolean value) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLC.glUniform1i(id, value ? GLC.GL_TRUE : GLC.GL_FALSE);
        GLC.checkError();
        return this;
    }

    @Override
    public UniformProgram<E> set(E key, int value) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLC.glUniform1i(id, value);
        GLC.checkError();
        return this;
    }

    @Override
    public UniformProgram<E> set(E key, float value) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLC.glUniform1f(id, value);
        GLC.checkError();
        return this;
    }

    @Override
    public UniformProgram<E> set(E key, float[] value) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLC.glUniform1fv(id, value);
        GLC.checkError();
        return this;
    }

    @Override
    public UniformProgram<E> set(E key, FloatBuffer buffer) throws GraphicsException {
        assertBinding();
        int id = uniform(key);
        GLC.glUniformMatrix4fv(id, true, buffer);
        GLC.checkError();
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
