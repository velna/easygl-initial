package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;
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

    Program set(String key, int value);

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

    Program set(String key, float value);

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

    default Program set(String key, Vector2f value) {
        return setVec2(key, value.x, value.y);
    }

    default Program set(String key, Vector3f value) {
        return setVec3(key, value.x, value.y, value.z);
    }

    default Program set(String key, Vector4f value) {
        return setVec4(key, value.x, value.y, value.z, value.w);
    }

    default Program set(String key, Matrix2f value) {
        return setMatrix2(key, value.get(new float[4]));
    }

    default Program set(String key, Matrix3f value) {
        return setMatrix3(key, value.get(new float[9]));
    }

    default Program set(String key, Matrix4f value) {
        return setMatrix4(key, value.get(new float[16]));
    }

    default Program set(String key, Matrix3x2f value) {
        return setMatrix3x2(key, value.get(new float[6]));
    }

    default Program set(String key, Matrix4x3f value) {
        return setMatrix4x3(key, value.get(new float[12]));
    }

    Program set(String key, Texture.Unit unit, Texture<?> texture);

    boolean getAttribute(ProgramAttribute.Bool attribute);

    int getAttribute(ProgramAttribute.Int attribute);

    @Support(since = Version.GL43)
    ProgramInterfaces interfaces();

    @Support(since = Version.GL43)
    @SuppressWarnings("unchecked")
    default <T extends ProgramInterface> T preload(T programInterface, int index, Program.PropertyKey... keys) {
        return (T) new ProgramInterfaceImpl(programInterface, index, keys);
    }

    int getUniformBlockIndex(String name);

    Program bindUniformBlock(int uniformBlockIndex, int bindingPoint);

    default Program bindUniformBlock(String name, int bindingPoint) {
        return bindUniformBlock(getUniformBlockIndex(name), bindingPoint);
    }

    static Program of() {
        return MetaHolder.Program.create();
    }

    record Binary(int format, ByteBuffer data) {
    }

    @Support(since = Version.GL43)
    enum PropertyKey implements IntEnum {
        NameLength("NAME_LENGTH"),
        Type("TYPE"),
        ArraySize("ARRAY_SIZE"),
        Offset("OFFSET"),
        BlockIndex("BLOCK_INDEX"),
        ArrayStride("ARRAY_STRIDE"),
        MatrixStride("MATRIX_STRIDE"),
        IsRowMajor("IS_ROW_MAJOR"),
        AtomicCounterBufferIndex("ATOMIC_COUNTER_BUFFER_INDEX"),
        TextureBuffer("TEXTURE_BUFFER"),
        BufferBinding("BUFFER_BINDING"),
        BufferDataSize("BUFFER_DATA_SIZE"),
        NumActiveVariables("NUM_ACTIVE_VARIABLES"),
        ActiveVariables("ACTIVE_VARIABLES"),//array/GL_NUM_ACTIVE_VARIABLES
        ReferencedByVertexShader("REFERENCED_BY_VERTEX_SHADER"),
        ReferencedByTessControlShader("REFERENCED_BY_TESS_CONTROL_SHADER"),
        ReferencedByTessEvaluationShader("REFERENCED_BY_TESS_EVALUATION_SHADER"),
        ReferencedByGeometryShader("REFERENCED_BY_GEOMETRY_SHADER"),
        ReferencedByFragmentShader("REFERENCED_BY_FRAGMENT_SHADER"),
        ReferencedByComputeShader("REFERENCED_BY_COMPUTE_SHADER"),
        NumCompatibleSubroutines("NUM_COMPATIBLE_SUBROUTINES"),
        CompatibleSubroutines("COMPATIBLE_SUBROUTINES"),
        TopLevelArraySize("TOP_LEVEL_ARRAY_SIZE"),
        TopLevelArrayStride("TOP_LEVEL_ARRAY_STRIDE"),
        Location("LOCATION"),
        LocationIndex("LOCATION_INDEX"),
        IsPerPatch("IS_PER_PATCH"),
        LocationComponent("LOCATION_COMPONENT"),
        TransformFeedbackBufferIndex("TRANSFORM_FEEDBACK_BUFFER_INDEX"),
        TransformFeedbackBufferStride("TRANSFORM_FEEDBACK_BUFFER_STRIDE");
        private final int value;

        PropertyKey(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
