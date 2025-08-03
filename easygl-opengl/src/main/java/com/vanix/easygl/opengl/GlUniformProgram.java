package com.vanix.easygl.opengl;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.Texture;
import com.vanix.easygl.core.graphics.Texture.Unit;
import com.vanix.easygl.core.graphics.UniformProgram;
import org.eclipse.collections.api.factory.primitive.ObjectIntMaps;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;

import java.nio.FloatBuffer;

public class GlUniformProgram extends AbstractProgram<UniformProgram> implements UniformProgram {

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static final BindTarget.Default<?> Target = new BindTarget.Default(BindingState.ofInt("Program", GLX::glUseProgram));
    private final MutableObjectIntMap<String> uniforms = ObjectIntMaps.mutable.of();

    @SuppressWarnings("unchecked")
    protected GlUniformProgram(int handle, Object... args) {
        super(handle, (String) args[0], (BindTarget.Default<UniformProgram>) Target);
    }

    protected GlUniformProgram(Object... args) {
        this(GLX.glCreateProgram(), args);
    }

    private int uniform(String key) throws GraphicsException {
        int ret = uniforms.getIfAbsentPutWithKey(key, k -> GLX.glGetUniformLocation(intHandle(), k));
        if (ret < 0) {
            throw new GraphicsException("Can not find uniform for name " + key);
        }
        return ret;
    }

    @Override
    public GlUniformProgram bind() {
        return (GlUniformProgram) super.bind();
    }

    @Override
    public UniformProgram set(String key, boolean value) throws GraphicsException {
        assertBinding();
        GLX.glUniform1i(uniform(key), value ? GLX.GL_TRUE : GLX.GL_FALSE);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram set(String key, int value) throws GraphicsException {
        assertBinding();
        GLX.glUniform1i(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram set(String key, float value) throws GraphicsException {
        assertBinding();
        GLX.glUniform1f(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram set(String key, float v1, float v2, float v3, float v4) throws GraphicsException {
        assertBinding();
        GLX.glUniform4f(uniform(key), v1, v2, v3, v4);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram set(String key, float[] value) throws GraphicsException {
        assertBinding();
        GLX.glUniform1fv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram set(String key, FloatBuffer buffer) throws GraphicsException {
        assertBinding();
        GLX.glUniformMatrix4fv(uniform(key), true, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public UniformProgram set(String key, Unit unit, Texture<?> texture) throws GraphicsException {
        unit.assertBinding();
        texture.assertBinding();
        return set(key, unit.ordinal());
    }

}
