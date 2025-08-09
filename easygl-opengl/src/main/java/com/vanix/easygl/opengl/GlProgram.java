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
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GlProgram extends AbstractBindable<BindTarget.Default<Program>, Program> implements Program {
    protected static final BindTarget.Default<Program> Target = new BindTarget.Default<>("Program", GlMetaService.ProgramMeta);

    private final MutableIntObjectMap<Shader> shaders = new IntObjectHashMap<>();
    private final MutableObjectIntMap<String> uniforms = ObjectIntMaps.mutable.of();

    protected GlProgram(Object... args) {
        this(GLX.glCreateProgram(), args);
    }

    protected GlProgram(int handle, Object... args) {
        super(handle, Target, GLX::glDeleteProgram);
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
    public Program link() {
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

    private int uniform(String key) {
        int ret = uniforms.getIfAbsentPutWithKey(key, k -> GLX.glGetUniformLocation(intHandle(), k));
        if (ret < 0) {
            throw new GraphicsException("Can not find uniform for name " + key);
        }
        return ret;
    }

    @Override
    public Program set(String key, int value) {
        assertBinding();
        GLX.glUniform1i(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec2(String key, int v1, int v2) {
        assertBinding();
        GLX.glUniform2i(uniform(key), v1, v2);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec3(String key, int v1, int v2, int v3) {
        assertBinding();
        GLX.glUniform3i(uniform(key), v1, v2, v3);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec4(String key, int v1, int v2, int v3, int v4) {
        assertBinding();
        GLX.glUniform4i(uniform(key), v1, v2, v3, v4);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec1(String key, int[] value) {
        assertBinding();
        GLX.glUniform1iv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec1(String key, IntBuffer buffer) {
        assertBinding();
        GLX.glUniform1iv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec2(String key, int[] value) {
        assertBinding();
        GLX.glUniform2iv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec2(String key, IntBuffer buffer) {
        assertBinding();
        GLX.glUniform2iv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec3(String key, int[] value) {
        assertBinding();
        GLX.glUniform3iv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec3(String key, IntBuffer buffer) {
        assertBinding();
        GLX.glUniform3iv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec4(String key, int[] value) {
        assertBinding();
        GLX.glUniform4iv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec4(String key, IntBuffer buffer) {
        assertBinding();
        GLX.glUniform4iv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsigned(String key, int value) {
        assertBinding();
        GLX.glUniform1ui(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsigned(String key, int v1, int v2) {
        assertBinding();
        GLX.glUniform2ui(uniform(key), v1, v2);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsigned(String key, int v1, int v2, int v3) {
        assertBinding();
        GLX.glUniform3ui(uniform(key), v1, v2, v3);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsigned(String key, int v1, int v2, int v3, int v4) {
        assertBinding();
        GLX.glUniform4ui(uniform(key), v1, v2, v3, v4);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsignedVec1(String key, int[] value) {
        assertBinding();
        GLX.glUniform1uiv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsignedVec1(String key, IntBuffer buffer) {
        assertBinding();
        GLX.glUniform1uiv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsignedVec2(String key, int[] value) {
        assertBinding();
        GLX.glUniform2uiv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsignedVec2(String key, IntBuffer buffer) {
        assertBinding();
        GLX.glUniform2uiv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsignedVec3(String key, int[] value) {
        assertBinding();
        GLX.glUniform3uiv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsignedVec3(String key, IntBuffer buffer) {
        assertBinding();
        GLX.glUniform3uiv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsignedVec4(String key, int[] value) {
        assertBinding();
        GLX.glUniform4uiv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setUnsignedVec4(String key, IntBuffer buffer) {
        assertBinding();
        GLX.glUniform4uiv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program set(String key, float value) {
        assertBinding();
        GLX.glUniform1f(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec2(String key, float v1, float v2) {
        assertBinding();
        GLX.glUniform2f(uniform(key), v1, v2);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec3(String key, float v1, float v2, float v3) {
        assertBinding();
        GLX.glUniform3f(uniform(key), v1, v2, v3);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec4(String key, float v1, float v2, float v3, float v4) {
        assertBinding();
        GLX.glUniform4f(uniform(key), v1, v2, v3, v4);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec1(String key, float[] value) {
        assertBinding();
        GLX.glUniform1fv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec1(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniform1fv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec2(String key, float[] value) {
        assertBinding();
        GLX.glUniform2fv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec2(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniform2fv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec3(String key, float[] value) {
        assertBinding();
        GLX.glUniform3fv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec3(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniform3fv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec4(String key, float[] value) {
        assertBinding();
        GLX.glUniform4fv(uniform(key), value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setVec4(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniform4fv(uniform(key), buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix2(String key, float[] value) {
        assertBinding();
        GLX.glUniformMatrix2fv(uniform(key), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix2(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniformMatrix2fv(uniform(key), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix3(String key, float[] value) {
        assertBinding();
        GLX.glUniformMatrix3fv(uniform(key), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix3(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniformMatrix3fv(uniform(key), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix4(String key, float[] value) {
        assertBinding();
        GLX.glUniformMatrix4fv(uniform(key), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix4(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniformMatrix4fv(uniform(key), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix2x3(String key, float[] value) {
        assertBinding();
        GLX.glUniformMatrix2x3fv(uniform(key), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix2x3(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniformMatrix2x3fv(uniform(key), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix3x2(String key, float[] value) {
        assertBinding();
        GLX.glUniformMatrix3x2fv(uniform(key), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix3x2(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniformMatrix3x2fv(uniform(key), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix2x4(String key, float[] value) {
        assertBinding();
        GLX.glUniformMatrix2x4fv(uniform(key), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix2x4(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniformMatrix2x4fv(uniform(key), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix4x2(String key, float[] value) {
        assertBinding();
        GLX.glUniformMatrix4x2fv(uniform(key), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix4x2(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniformMatrix4x2fv(uniform(key), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix3x4(String key, float[] value) {
        assertBinding();
        GLX.glUniformMatrix3x4fv(uniform(key), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix3x4(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniformMatrix3x4fv(uniform(key), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix4x3(String key, float[] value) {
        assertBinding();
        GLX.glUniformMatrix4x3fv(uniform(key), false, value);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setMatrix4x3(String key, FloatBuffer buffer) {
        assertBinding();
        GLX.glUniformMatrix4x3fv(uniform(key), false, buffer);
        GLX.checkError();
        return this;
    }

    @Override
    public Program set(String key, Texture.Unit unit, Texture<?> texture) {
        unit.assertBinding();
        texture.assertBinding();
        return set(key, unit.ordinal());
    }
}
