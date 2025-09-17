package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.graphics.program.Uniform;
import com.vanix.easygl.core.graphics.program.UniformBlock;
import com.vanix.easygl.opengl.program.G20Uniform;
import com.vanix.easygl.opengl.program.Gl31UniformBlock;
import org.eclipse.collections.api.factory.primitive.ObjectIntMaps;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;
import org.joml.*;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class GlProgram extends AbstractBindable<BindTarget.Default<Program>, Program> implements Program {
    private final MutableObjectIntMap<String> uniforms = ObjectIntMaps.mutable.of();
    private GlProgramInterfaces interfaces;

    protected GlProgram() {
        this(GLX.glCreateProgram());
    }

    protected GlProgram(int handle) {
        super(handle, Target, GLX::glDeleteProgram);
    }

    @Override
    public Program attach(Shader shader) {
        GLX.glAttachShader(intHandle(), shader.intHandle());
        GLX.checkError();
        return self();
    }

    @Override
    public ShaderArray getAttachedShaders() {
        int size = GLX.glGetProgrami(intHandle(), GLX.GL_ATTACHED_SHADERS);
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocInt(size);
            GLX.glGetAttachedShaders(intHandle(), null, buffer);
            List<Shader> shaders = new ArrayList<>(size);
            while (buffer.hasRemaining()) {
                shaders.add(new GlShader(buffer.get(), null));
            }
            return new GlShaderArray(shaders);
        }
    }

    @Override
    public Program detach(Shader shader) {
        GLX.glDetachShader(intHandle(), shader.intHandle());
        GLX.checkError();
        return self();
    }

    @Override
    public Program bindFragData(FrameInnerBuffer.DrawBuffer drawBuffer, String varyingOutVariableName) {
        GLX.glBindFragDataLocation(intHandle(), drawBuffer.value(), varyingOutVariableName);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameInnerBuffer.DrawBuffer getFragData(String varyingOutVariableName) {
        int location = GLX.glGetFragDataLocation(intHandle(), varyingOutVariableName);
        return location < 0 ? null : FrameInnerBuffer.DrawBuffer.of(location);
    }

    @Override
    public Program bindFragData(FrameInnerBuffer.DrawBuffer drawBuffer, String varyingOutVariableName, int index) {
        GLX.glBindFragDataLocationIndexed(intHandle(), drawBuffer.value(), index, varyingOutVariableName);
        GLX.checkError();
        return this;
    }

    @Override
    public int getFragDataIndex(String varyingOutVariableName) {
        return GLX.glGetFragDataIndex(intHandle(), varyingOutVariableName);
    }

    @Override
    public Program link() {
        int program = intHandle();
        GLX.glLinkProgram(program);
        try (var stack = MemoryStack.stackPush()) {
            IntBuffer success = stack.mallocInt(1);
            GLX.glGetProgramiv(program, GLX.GL_LINK_STATUS, success);
            if (success.get() == 0) {
                String infoLog = GLX.glGetProgramInfoLog(program);
                throw new GraphicsException("error link program: " + infoLog);
            }
            return self();
        }
    }

    @Override
    public Program validate() {
        GLX.glValidateProgram(intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Program setBinaryRetrievable(boolean retrievable) {
        assertBinding();
        GLX.glProgramParameteri(intHandle(), GLX.GL_PROGRAM_BINARY_RETRIEVABLE_HINT, retrievable ? GLX.GL_TRUE : GLX.GL_FALSE);
        return this;
    }

    @Override
    public Binary getBinary() {
        assertBinding();
        int len = GLX.glGetProgrami(intHandle(), GLX.GL_PROGRAM_BINARY_LENGTH);
        if (len <= 0) {
            GLX.checkError();
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(len);
        int[] format = new int[1];
        GLX.glGetProgramBinary(intHandle(), null, format, buffer);
        GLX.checkError();
        return new Binary(format[0], buffer);
    }

    @Override
    public Program loadBinary(int format, ByteBuffer data) {
        assertBinding();
        GLX.glProgramBinary(intHandle(), format, data);
        GLX.checkError();
        return this;
    }

    @Override
    public Program setSeparable(boolean separable) {
        assertBinding();
        GLX.glProgramParameteri(intHandle(), GLX.GL_PROGRAM_SEPARABLE, separable ? GLX.GL_TRUE : GLX.GL_FALSE);
        return this;
    }

    @Override
    public void close() {
        super.close();
    }

    private int uniform(String key) {
        int ret = uniforms.getIfAbsentPutWithKey(key, this::getUniformLocation);
        if (ret < 0) {
            throw new GraphicsException("Can not find uniform for name " + key);
        }
        return ret;
    }

    private int getUniformLocation(String key) {
        return GLX.glGetUniformLocation(intHandle(), key);
    }

    @Override
    public Program setInt(String key, int value) {
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
    public Program setFloat(String key, float value) {
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
    public Program setMatrix2(String key, Matrix2f value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(4);
            return setMatrix2(key, value.get(buffer));
        }
    }

    @Override
    public Program setMatrix3(String key, Matrix3f value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(9);
            return setMatrix3(key, value.get(buffer));
        }
    }

    @Override
    public Program setMatrix4(String key, Matrix4f value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(16);
            return setMatrix4(key, value.get(buffer));
        }
    }

    @Override
    public Program setMatrix3x2(String key, Matrix3x2f value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(6);
            return setMatrix3x2(key, value.get(buffer));
        }
    }

    @Override
    public Program setMatrix4x3(String key, Matrix4x3f value) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(12);
            return setMatrix4x3(key, value.get(buffer));
        }
    }

    @Override
    public int getInt(String key) {
        return GLX.glGetUniformi(intHandle(), uniform(key));
    }

    @Override
    public int[] getInt(String key, int[] value) {
        GLX.glGetUniformiv(intHandle(), uniform(key), value);
        return value;
    }

    @Override
    public IntBuffer getInt(String key, IntBuffer buffer) {
        GLX.glGetUniformiv(intHandle(), uniform(key), buffer);
        return buffer;
    }

    private <T> T getInt0(String key, T value, int size, BiFunction<T, IntBuffer, T> setter) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocInt(size);
            GLX.glGetUniformiv(intHandle(), uniform(key), buffer);
            return setter.apply(value, buffer);
        }
    }

    @Override
    public Vector2i getVec2(String key, Vector2i vec) {
        return getInt0(key, vec, 2, Vector2i::set);
    }

    @Override
    public Vector3i getVec3(String key, Vector3i vec) {
        return getInt0(key, vec, 3, Vector3i::set);
    }

    @Override
    public Vector4i getVec4(String key, Vector4i vec) {
        return getInt0(key, vec, 4, Vector4i::set);
    }

    private <T> T getFloat0(String key, T value, int size, BiFunction<T, FloatBuffer, T> setter) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(size);
            GLX.glGetUniformfv(intHandle(), uniform(key), buffer);
            return setter.apply(value, buffer);
        }
    }

    @Override
    public float getFloat(String key) {
        return GLX.glGetUniformf(intHandle(), uniform(key));
    }

    @Override
    public float[] getFloat(String key, float[] value) {
        GLX.glGetUniformfv(intHandle(), uniform(key), value);
        return value;
    }

    @Override
    public FloatBuffer getFloat(String key, FloatBuffer buffer) {
        GLX.glGetUniformfv(intHandle(), uniform(key), buffer);
        return buffer;
    }

    @Override
    public Vector2f getVec2(String key, Vector2f vec) {
        return getFloat0(key, vec, 2, Vector2f::set);
    }

    @Override
    public Vector3f getVec3(String key, Vector3f vec) {
        return getFloat0(key, vec, 3, Vector3f::set);
    }

    @Override
    public Vector4f getVec4(String key, Vector4f vec) {
        return getFloat0(key, vec, 4, Vector4f::set);
    }

    @Override
    public Matrix2f getMatrix2(String key, Matrix2f matrix) {
        return getFloat0(key, matrix, 4, Matrix2f::set);
    }

    @Override
    public Matrix3f getMatrix3(String key, Matrix3f matrix) {
        return getFloat0(key, matrix, 9, Matrix3f::set);
    }

    @Override
    public Matrix3x2f getMatrix3X2(String key, Matrix3x2f matrix) {
        return getFloat0(key, matrix, 6, Matrix3x2f::set);
    }

    @Override
    public Matrix4f getMatrix4(String key, Matrix4f matrix) {
        return getFloat0(key, matrix, 16, Matrix4f::set);
    }

    @Override
    public Matrix4x3f getMatrix4X3(String key, Matrix4x3f matrix) {
        return getFloat0(key, matrix, 12, Matrix4x3f::set);
    }

    private <T> T getDouble0(String key, T value, int size, BiFunction<T, DoubleBuffer, T> setter) {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocDouble(size);
            GLX.glGetUniformdv(intHandle(), uniform(key), buffer);
            return setter.apply(value, buffer);
        }
    }

    @Override
    public double getDouble(String key) {
        return GLX.glGetUniformf(intHandle(), uniform(key));
    }

    @Override
    public double[] getDouble(String key, double[] value) {
        GLX.glGetUniformdv(intHandle(), uniform(key), value);
        return value;
    }

    @Override
    public DoubleBuffer getDouble(String key, DoubleBuffer buffer) {
        GLX.glGetUniformdv(intHandle(), uniform(key), buffer);
        return buffer;
    }

    @Override
    public Vector2d getVec2(String key, Vector2d vec) {
        return getDouble0(key, vec, 2, Vector2d::set);
    }

    @Override
    public Vector3d getVec3(String key, Vector3d vec) {
        return getDouble0(key, vec, 3, Vector3d::set);
    }

    @Override
    public Vector4d getVec4(String key, Vector4d vec) {
        return getDouble0(key, vec, 4, Vector4d::set);
    }

    @Override
    public Matrix2d getMatrix2(String key, Matrix2d matrix) {
        return getDouble0(key, matrix, 4, Matrix2d::set);
    }

    @Override
    public Matrix3d getMatrix3(String key, Matrix3d matrix) {
        return getDouble0(key, matrix, 9, Matrix3d::set);
    }

    @Override
    public Matrix3x2d getMatrix3X2(String key, Matrix3x2d matrix) {
        return getDouble0(key, matrix, 6, Matrix3x2d::set);
    }

    @Override
    public Matrix4d getMatrix4(String key, Matrix4d matrix) {
        return getDouble0(key, matrix, 16, Matrix4d::set);
    }

    @Override
    public Matrix4x3d getMatrix4X3(String key, Matrix4x3d matrix) {
        return getDouble0(key, matrix, 12, Matrix4x3d::set);
    }

    @Override
    public int getUnsigned(String key) {
        return GLX.glGetUniformui(intHandle(), uniform(key));
    }

    @Override
    public int[] getUnsigned(String key, int[] value) {
        GLX.glGetUniformuiv(intHandle(), uniform(key), value);
        return value;
    }

    @Override
    public IntBuffer getUnsigned(String key, IntBuffer buffer) {
        GLX.glGetUniformuiv(intHandle(), uniform(key), buffer);
        return buffer;
    }

    @Override
    public Program setTexture(String key, TextureUnit unit, Texture<?> texture) {
        unit.assertBinding();
        texture.assertBinding();
        return setInt(key, unit.index());
    }

    @Override
    public GlProgramInterfaces interfaces() {
        if (interfaces == null) {
            interfaces = new GlProgramInterfaces(this);
        }
        return interfaces;
    }

    @Override
    public boolean containsUniform(String name) {
        return uniforms.getIfAbsentPutWithKey(name, this::getUniformLocation) >= 0;
    }

    @Override
    public List<String> uniformNames() {
        int uniformCount = getAttribute(ProgramAttribute.ActiveUniforms);
        List<String> names;
        if (uniforms.size() < uniformCount) {
            names = new ArrayList<>(uniformCount);
            for (int i = 0; i < uniformCount; i++) {
                var name = GLX.glGetActiveUniformName(intHandle(), i);
                uniforms.put(name, i);
                names.add(name);
            }
        } else {
            String[] array = new String[uniformCount];
            for (var entry : uniforms.keyValuesView()) {
                array[entry.getTwo()] = entry.getOne();
            }
            names = Arrays.asList(array);
        }
        return names;
    }

    @Override
    public Uniform getUniform(int index) {
        if (GlGraphics.CAPABILITIES.OpenGL43) {
            return interfaces.uniform().getResource(index);
        }
        try (var stack = MemoryStack.stackPush()) {
            var length = stack.mallocInt(1);
            var size = stack.mallocInt(1);
            var type = stack.mallocInt(1);
            var name = stack.malloc(getAttribute(ProgramAttribute.ActiveUniformMaxLength));
            GLX.glGetActiveUniform(intHandle(), index, length, size, type, name);
            GLX.checkError();
            return new G20Uniform(this, index, Cache.DataType.get(type.get()), size.get(), MemoryUtil.memUTF8(name, length.get()));
        }
    }

    @Override
    public UniformBlock getUniformBlock(String name) {
        if (GlGraphics.CAPABILITIES.OpenGL43) {
            return interfaces.uniformBlock().getResource(name);
        }
        int index = GLX.glGetUniformBlockIndex(intHandle(), name);
        if (index == GLX.GL_INVALID_INDEX) {
            throw new NoSuchElementException("No uniform block of name find: " + name);
        }
        return new Gl31UniformBlock(this, index, name);
    }

    @Override
    public boolean getAttribute(ProgramAttribute.Bool attribute) {
        return GLX.glGetProgrami(intHandle(), attribute.value()) == GLX.GL_TRUE;
    }

    @Override
    public int getAttribute(ProgramAttribute.Int attribute) {
        return GLX.glGetProgrami(intHandle(), attribute.value());
    }

    @Override
    public Program bindVertexAttribute(VertexAttribute vertexAttribute, String variableName) {
        GLX.glBindAttribLocation(intHandle(), vertexAttribute.value(), variableName);
        GLX.checkError();
        return this;
    }

    @Override
    public Variable getActiveVertexAttribute(VertexAttribute vertexAttribute) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer lengthBuffer = stack.mallocInt(1);
            IntBuffer sizeBuffer = stack.mallocInt(1);
            IntBuffer typeBuffer = stack.mallocInt(1);
            ByteBuffer nameBuffer = stack.malloc(getAttribute(ProgramAttribute.ActiveAttributeMaxLength));
            GLX.glGetActiveAttrib(intHandle(), vertexAttribute.value(), lengthBuffer, sizeBuffer, typeBuffer, nameBuffer);
            GLX.checkError();
            return new Variable(sizeBuffer.get(), Cache.DataType.get(typeBuffer.get()), MemoryUtil.memUTF8(nameBuffer, lengthBuffer.get()));
        }
    }

    @Override
    public int getVertexAttributeLocation(String name) {
        return GLX.glGetAttribLocation(intHandle(), name);
    }

    @Override
    public Program transformFeedbackVaryings(boolean interleaved, String... varyings) {
        GLX.glTransformFeedbackVaryings(intHandle(), varyings, interleaved ? GLX.GL_INTERLEAVED_ATTRIBS : GLX.GL_SEPARATE_ATTRIBS);
        GLX.checkError();
        return this;
    }

    @Override
    public Variable getTransformFeedbackVarying(int varyingsIndex) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer lengthBuffer = stack.mallocInt(1);
            IntBuffer sizeBuffer = stack.mallocInt(1);
            IntBuffer typeBuffer = stack.mallocInt(1);
            ByteBuffer nameBuffer = stack.malloc(getAttribute(ProgramAttribute.ActiveAttributeMaxLength));
            GLX.glGetTransformFeedbackVarying(intHandle(), varyingsIndex, lengthBuffer, sizeBuffer, typeBuffer, nameBuffer);
            GLX.checkError();
            return new Variable(sizeBuffer.get(), Cache.DataType.get(typeBuffer.get()), MemoryUtil.memUTF8(nameBuffer, lengthBuffer.get()));
        }
    }
}
