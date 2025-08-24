package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.graphics.program.UniformBlock;
import org.joml.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

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

    Program detach(Shader shader);

    Program link();

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

    default Program setMatrix2(String key, Matrix2f value) {
        return setMatrix2(key, value.get(new float[4]));
    }

    default Program setMatrix3(String key, Matrix3f value) {
        return setMatrix3(key, value.get(new float[9]));
    }

    default Program setMatrix4(String key, Matrix4f value) {
        return setMatrix4(key, value.get(new float[16]));
    }

    default Program setMatrix3x2(String key, Matrix3x2f value) {
        return setMatrix3x2(key, value.get(new float[6]));
    }

    default Program setMatrix4x3(String key, Matrix4x3f value) {
        return setMatrix4x3(key, value.get(new float[12]));
    }

    Program setTexture(String key, Texture.Unit unit, Texture<?> texture);

    boolean getAttribute(ProgramAttribute.Bool attribute);

    int getAttribute(ProgramAttribute.Int attribute);

    @Support(since = Version.GL43)
    ProgramInterfaces interfaces();

    @Support(since = Version.GL31)
    UniformBlock getUniformBlock(String name);

    static Program of() {
        return MetaHolder.Program.create();
    }

    record Binary(int format, ByteBuffer data) {
    }

}
