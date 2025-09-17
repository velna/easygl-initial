package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.graphics.program.Uniform;
import com.vanix.easygl.core.graphics.program.UniformBlock;
import org.joml.*;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

public interface Program extends Bindable<BindTarget.Default<Program>, Program> {

    BindTarget.Default<Program> Target = new BindTarget.Default<>("Program", MetaHolder.Program);

    Program attach(Shader shader);

    default Program attachVertex(String shaderSource) {
        return attach(Shader.Type.Vertex, shaderSource);
    }

    default Program attachFragment(String shaderSource) {
        return attach(Shader.Type.Fragment, shaderSource);
    }

    default Program attachGeometry(String shaderSource) {
        return attach(Shader.Type.Geometry, shaderSource);
    }

    default Program attach(Shader.Type type, String shaderSource) {
        try (var shader = Shader.of(type).source(shaderSource).compile()) {
            return attach(shader);
        }
    }

    default Program attach(Shader.Type type, InputStream inputStream) throws IOException {
        try (var shader = Shader.of(type).source(inputStream).compile()) {
            return attach(shader);
        }
    }

    default Program attachResource(Shader.Type type, String resource) throws IOException {
        return attach(type, Thread.currentThread().getContextClassLoader().getResourceAsStream(resource));
    }

    ShaderArray getAttachedShaders();

    Program detach(Shader shader);

    @Support(since = Version.GL30)
    Program bindFragData(FrameInnerBuffer.DrawBuffer drawBuffer, String varyingOutVariableName);

    @Support(since = Version.GL30)
    @Nullable
    FrameInnerBuffer.DrawBuffer getFragData(String varyingOutVariableName);

    @Support(since = Version.GL32)
    Program bindFragData(FrameInnerBuffer.DrawBuffer drawBuffer, String varyingOutVariableName, int index);

    @Support(since = Version.GL32)
    int getFragDataIndex(String varyingOutVariableName);

    Program link();

    Program validate();

    @Support(since = Version.GL41)
    Program setBinaryRetrievable(boolean retrievable);

    @Support(since = Version.GL41)
    Binary getBinary();

    @Support(since = Version.GL41)
    Program loadBinary(int format, ByteBuffer data);

    @Support(since = Version.GL41)
    default Program loadBinary(Binary binary) {
        return loadBinary(binary.format, binary.data);
    }

    @Support(since = Version.GL41)
    Program setSeparable(boolean separable);

    default Program setBoolean(String key, boolean value) {
        return setInt(key, value ? 1 : 0);
    }

    //region Set Uniforms
    Program setInt(String key, int value);

    Program setVec1(String key, int[] value);

    Program setVec1(String key, IntBuffer buffer);

    Program setVec2(String key, int v1, int v2);

    Program setVec2(String key, int[] value);

    Program setVec2(String key, IntBuffer buffer);

    Program setVec3(String key, int v1, int v2, int v3);

    Program setVec3(String key, int[] value);

    Program setVec3(String key, IntBuffer buffer);

    Program setVec4(String key, int v1, int v2, int v3, int v4);

    Program setVec4(String key, int[] value);

    Program setVec4(String key, IntBuffer buffer);

    Program setUnsigned(String key, int value);

    Program setUnsigned(String key, int v1, int v2);

    Program setUnsigned(String key, int v1, int v2, int v3);

    Program setUnsigned(String key, int v1, int v2, int v3, int v4);

    Program setUnsignedVec1(String key, int[] value);

    Program setUnsignedVec1(String key, IntBuffer buffer);

    Program setUnsignedVec2(String key, int[] value);

    Program setUnsignedVec2(String key, IntBuffer buffer);

    Program setUnsignedVec3(String key, int[] value);

    Program setUnsignedVec3(String key, IntBuffer buffer);

    Program setUnsignedVec4(String key, int[] value);

    Program setUnsignedVec4(String key, IntBuffer buffer);

    Program setFloat(String key, float value);

    Program setVec2(String key, float v1, float v2);

    Program setVec3(String key, float v1, float v2, float v3);

    Program setVec4(String key, float v1, float v2, float v3, float v4);

    Program setVec1(String key, float[] value);

    Program setVec1(String key, FloatBuffer buffer);

    Program setVec2(String key, float[] value);

    Program setVec2(String key, FloatBuffer buffer);

    Program setVec3(String key, float[] value);

    Program setVec3(String key, FloatBuffer buffer);

    Program setVec4(String key, float[] value);

    Program setVec4(String key, FloatBuffer buffer);

    Program setMatrix2(String key, float[] value);

    Program setMatrix2(String key, FloatBuffer buffer);

    Program setMatrix3(String key, float[] value);

    Program setMatrix3(String key, FloatBuffer buffer);

    Program setMatrix4(String key, float[] value);

    Program setMatrix4(String key, FloatBuffer buffer);

    Program setMatrix2x3(String key, float[] value);

    Program setMatrix2x3(String key, FloatBuffer buffer);

    Program setMatrix3x2(String key, float[] value);

    Program setMatrix3x2(String key, FloatBuffer buffer);

    Program setMatrix2x4(String key, float[] value);

    Program setMatrix2x4(String key, FloatBuffer buffer);

    Program setMatrix4x2(String key, float[] value);

    Program setMatrix4x2(String key, FloatBuffer buffer);

    Program setMatrix3x4(String key, float[] value);

    Program setMatrix3x4(String key, FloatBuffer buffer);

    Program setMatrix4x3(String key, float[] value);

    Program setMatrix4x3(String key, FloatBuffer buffer);

    default Program setVec2(String key, Vector2f value) {
        return setVec2(key, value.x, value.y);
    }

    default Program setVec3(String key, Vector3f value) {
        return setVec3(key, value.x, value.y, value.z);
    }

    default Program setVec4(String key, Vector4f value) {
        return setVec4(key, value.x, value.y, value.z, value.w);
    }

    Program setMatrix2(String key, Matrix2f value);

    Program setMatrix3(String key, Matrix3f value);

    Program setMatrix4(String key, Matrix4f value);

    Program setMatrix3x2(String key, Matrix3x2f value);

    Program setMatrix4x3(String key, Matrix4x3f value);
    //endregion

    //region Get Uniforms
    int getInt(String key);

    int[] getInt(String key, int[] value);

    IntBuffer getInt(String key, IntBuffer buffer);

    Vector2i getVec2(String key, Vector2i vec);

    Vector3i getVec3(String key, Vector3i vec);

    Vector4i getVec4(String key, Vector4i vec);

    int getUnsigned(String key);

    int[] getUnsigned(String key, int[] value);

    IntBuffer getUnsigned(String key, IntBuffer buffer);

    float getFloat(String key);

    float[] getFloat(String key, float[] value);

    FloatBuffer getFloat(String key, FloatBuffer buffer);

    Vector2f getVec2(String key, Vector2f vec);

    Vector3f getVec3(String key, Vector3f vec);

    Vector4f getVec4(String key, Vector4f vec);

    Matrix2f getMatrix2(String key, Matrix2f matrix);

    Matrix3f getMatrix3(String key, Matrix3f matrix);

    Matrix3x2f getMatrix3X2(String key, Matrix3x2f matrix);

    Matrix4f getMatrix4(String key, Matrix4f matrix);

    Matrix4x3f getMatrix4X3(String key, Matrix4x3f matrix);

    @Support(since = Version.GL40)
    double getDouble(String key);

    @Support(since = Version.GL40)
    double[] getDouble(String key, double[] value);

    @Support(since = Version.GL40)
    DoubleBuffer getDouble(String key, DoubleBuffer buffer);

    @Support(since = Version.GL40)
    Vector2d getVec2(String key, Vector2d vec);

    @Support(since = Version.GL40)
    Vector3d getVec3(String key, Vector3d vec);

    @Support(since = Version.GL40)
    Vector4d getVec4(String key, Vector4d vec);

    @Support(since = Version.GL40)
    Matrix2d getMatrix2(String key, Matrix2d matrix);

    @Support(since = Version.GL40)
    Matrix3d getMatrix3(String key, Matrix3d matrix);

    @Support(since = Version.GL40)
    Matrix3x2d getMatrix3X2(String key, Matrix3x2d matrix);

    @Support(since = Version.GL40)
    Matrix4d getMatrix4(String key, Matrix4d matrix);

    @Support(since = Version.GL40)
    Matrix4x3d getMatrix4X3(String key, Matrix4x3d matrix);
    //endregion

    Program setTexture(String key, TextureUnit unit, Texture<?> texture);

    boolean getAttribute(ProgramAttribute.Bool attribute);

    int getAttribute(ProgramAttribute.Int attribute);

    boolean containsUniform(String name);

    @Support(since = Version.GL31)
    List<String> uniformNames();

    Uniform getUniform(int index);

    @Support(since = Version.GL43)
    ProgramInterfaces interfaces();

    @Support(since = Version.GL31)
    UniformBlock getUniformBlock(String name);

    Program bindVertexAttribute(VertexAttribute vertexAttribute, String variableName);

    Variable getActiveVertexAttribute(VertexAttribute vertexAttribute);

    int getVertexAttributeLocation(String name);

    Program transformFeedbackVaryings(boolean interleaved, String... varyings);

    Variable getTransformFeedbackVarying(int varyingsIndex);

    static Program of() {
        return MetaHolder.Program.create();
    }

    record Binary(int format, ByteBuffer data) {
    }

    record Variable(int size, DataType dataType, String name) {}

}
