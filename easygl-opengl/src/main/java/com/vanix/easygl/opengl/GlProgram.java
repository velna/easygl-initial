package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.Shader;
import com.vanix.easygl.core.graphics.Texture;
import org.eclipse.collections.api.factory.primitive.ObjectIntMaps;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GlProgram extends AbstractBindable<BindTarget.Default<Program>, Program> implements Program {
    protected static final BindTarget.Default<Program> Target = new BindTarget.Default<>("Program", GlMetaService.ProgramMeta);

    private final String id;
    private final MutableIntObjectMap<Shader> shaders = new IntObjectHashMap<>();
    private final MutableObjectIntMap<String> uniforms = ObjectIntMaps.mutable.of();

    protected GlProgram(Object... args) {
        this(GLX.glCreateProgram(), args);
    }

    protected GlProgram(int handle, Object... args) {
        super(handle, Target, GLX::glDeleteProgram);
        this.id = (String) args[0];
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Program attach(Shader shader) {
        shaders.getIfAbsentPut(shader.intHandle(), () -> {
            GLX.glAttachShader(intHandle(), shader.intHandle());
            GLX.checkError();
            return shader;
        });
        return self();
    }

    @Override
    public Program detach(Shader shader) {
        if (shaders.remove(shader.intHandle()) != null) {
            GLX.glDetachShader(intHandle(), shader.intHandle());
            GLX.checkError();
        } else {
            throw new IllegalStateException("Shader never attached.");
        }
        return self();
    }

    @Override
    public Program link()  {
        int program = intHandle();
        GLX.glLinkProgram(program);
        IntBuffer success = MemoryStack.stackMallocInt(1);
        GL20.glGetProgramiv(program, GL20.GL_LINK_STATUS, success);
        if (success.get() == 0) {
            String infoLog = GL20.glGetProgramInfoLog(program);
            throw new GraphicsException("error link program: " + infoLog);
        }
        return self();
    }

    @Override
    public void close() {
        super.close();
        shaders.forEach(Shader::close);
        shaders.clear();
    }

    private int uniform(String key)  {
        int ret = uniforms.getIfAbsentPutWithKey(key, k -> GLX.glGetUniformLocation(intHandle(), k));
        if (ret < 0) {
            throw new GraphicsException("Can not find uniform for name " + key);
        }
        return ret;
    }

    @Override
    public Program set(String key, boolean value)  {
        assertBinding();
        GLX.glUniform1i(uniform(key), value ? GLX.GL_TRUE : GLX.GL_FALSE);
        GLX.checkError();
        return this;
    }

    @Override
    public Program set(String key, int value)  {
        assertBinding();
        GLX.glUniform1i(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program set(String key, float value)  {
        assertBinding();
        GLX.glUniform1f(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program set(String key, float v1, float v2, float v3, float v4)  {
        assertBinding();
        GLX.glUniform4f(uniform(key), v1, v2, v3, v4);
        GLX.checkError();
        return this;
    }

    @Override
    public Program set(String key, float[] value)  {
        assertBinding();
        GLX.glUniform1fv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program set(String key, FloatBuffer buffer)  {
        assertBinding();
        GLX.glUniform1fv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program set(String key, Matrix4f value) {
        assertBinding();
        GLX.glUniformMatrix4fv(uniform(key), false, value.get(new float[16]));
        GLX.checkError();
        return this;
    }

    @Override
    public Program set(String key, Texture.Unit unit, Texture<?> texture)  {
        unit.assertBinding();
        texture.assertBinding();
        return set(key, unit.ordinal());
    }
}
