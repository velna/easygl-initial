package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

public interface ProgramResource<T extends ProgramResource<T>> {

    Program program();

    T preLoad(PropertyKey... keys);

    @Support(since = Version.GL43)
    enum PropertyKey implements IntEnum {
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

    //region Real Resources
    interface Uniform extends
            Named<Uniform>,
            Type<Uniform>,
            ArraySize<Uniform>,
            Offset<Uniform>,
            BlockIndex<Uniform>,
            ArrayStride<Uniform>,
            MatrixStride<Uniform>,
            IsRowMajor<Uniform>,
            AtomicCounterBufferIndex<Uniform>,
            ReferencedByTessEvaluationShader<Uniform>,
            ReferencedByComputeShader<Uniform>,
            ReferencedByVertexShader<Uniform>,
            ReferencedByGeometryShader<Uniform>,
            ReferencedByFragmentShader<Uniform>,
            ReferencedByTessControlShader<Uniform>,
            Location<Uniform> {
    }

    interface UniformBlock extends
            Named<UniformBlock>,
            BufferBinding<UniformBlock>,
            BufferDataSize<UniformBlock>,
            NumActiveVariables<UniformBlock>,
            ActiveVariables<UniformBlock>,
            ReferencedByTessEvaluationShader<UniformBlock>,
            ReferencedByComputeShader<UniformBlock>,
            ReferencedByVertexShader<UniformBlock>,
            ReferencedByGeometryShader<UniformBlock>,
            ReferencedByFragmentShader<UniformBlock>,
            ReferencedByTessControlShader<UniformBlock> {
    }

    interface AtomicCounterBuffer extends
            BufferBinding<AtomicCounterBuffer>,
            BufferDataSize<AtomicCounterBuffer>,
            NumActiveVariables<AtomicCounterBuffer>,
            ActiveVariables<AtomicCounterBuffer> {

    }

    interface ProgramInput extends
            Named<ProgramInput>,
            Type<ProgramInput>,
            ArraySize<ProgramInput>,
            ReferencedByTessEvaluationShader<ProgramInput>,
            ReferencedByComputeShader<ProgramInput>,
            ReferencedByVertexShader<ProgramInput>,
            ReferencedByGeometryShader<ProgramInput>,
            ReferencedByFragmentShader<ProgramInput>,
            ReferencedByTessControlShader<ProgramInput>,
            Location<ProgramInput>,
            IsPerPatch<ProgramInput>,
            LocationComponent<ProgramInput> {

    }

    interface ProgramOutput extends
            Named<ProgramOutput>,
            Type<ProgramOutput>,
            ArraySize<ProgramOutput>,
            ReferencedByTessEvaluationShader<ProgramOutput>,
            ReferencedByComputeShader<ProgramOutput>,
            ReferencedByVertexShader<ProgramOutput>,
            ReferencedByGeometryShader<ProgramOutput>,
            ReferencedByFragmentShader<ProgramOutput>,
            ReferencedByTessControlShader<ProgramOutput>,
            Location<ProgramOutput>,
            LocationIndex<ProgramOutput>,
            IsPerPatch<ProgramOutput>,
            LocationComponent<ProgramOutput> {

    }

    interface SubroutineUniform<T extends ProgramResource<T>> extends
            Named<T>,
            ArraySize<T>,
            NumCompatibleSubroutines<T>,
            CompatibleSubroutines<T>,
            Location<T> {

    }

    interface TransformFeedbackVarying extends
            Named<TransformFeedbackVarying>,
            Type<TransformFeedbackVarying>,
            ArraySize<TransformFeedbackVarying>,
            Offset<TransformFeedbackVarying>,
            TransformFeedbackBufferIndex<TransformFeedbackVarying> {

    }

    interface BufferVariable extends
            Named<BufferVariable>,
            Type<BufferVariable>,
            ArraySize<BufferVariable>,
            Offset<BufferVariable>,
            BlockIndex<BufferVariable>,
            ArrayStride<BufferVariable>,
            MatrixStride<BufferVariable>,
            IsRowMajor<BufferVariable>,
            ReferencedByTessEvaluationShader<BufferVariable>,
            ReferencedByComputeShader<BufferVariable>,
            ReferencedByVertexShader<BufferVariable>,
            ReferencedByGeometryShader<BufferVariable>,
            ReferencedByFragmentShader<BufferVariable>,
            ReferencedByTessControlShader<BufferVariable>,
            TopLevelArraySize<BufferVariable>,
            TopLevelArrayStride<BufferVariable> {

    }

    interface ShaderStorageBlock extends
            Named<ShaderStorageBlock>,
            BufferBinding<ShaderStorageBlock>,
            BufferDataSize<ShaderStorageBlock>,
            NumActiveVariables<ShaderStorageBlock>,
            ActiveVariables<ShaderStorageBlock>,
            ReferencedByTessEvaluationShader<ShaderStorageBlock>,
            ReferencedByComputeShader<ShaderStorageBlock>,
            ReferencedByVertexShader<ShaderStorageBlock>,
            ReferencedByGeometryShader<ShaderStorageBlock>,
            ReferencedByFragmentShader<ShaderStorageBlock>,
            ReferencedByTessControlShader<ShaderStorageBlock> {

    }

    interface TransformFeedbackBuffer extends
            BufferBinding<TransformFeedbackBuffer>,
            NumActiveVariables<TransformFeedbackBuffer>,
            ActiveVariables<TransformFeedbackBuffer>,
            TransformFeedbackBufferIndex<TransformFeedbackBuffer>,
            TransformFeedbackBufferStride<TransformFeedbackBuffer> {

    }

    interface VertexSubroutine extends Named<VertexSubroutine> {
    }

    interface TessControlSubroutine extends Named<TessControlSubroutine> {
    }

    interface TessEvaluationSubroutine extends Named<TessEvaluationSubroutine> {
    }

    interface GeometrySubroutine extends Named<GeometrySubroutine> {
    }

    interface FragmentSubroutine extends Named<FragmentSubroutine> {
    }

    interface ComputeSubroutine extends Named<ComputeSubroutine> {
    }

    interface VertexSubroutineUniform extends SubroutineUniform<VertexSubroutineUniform> {
    }

    interface TessControlSubroutineUniform extends SubroutineUniform<TessControlSubroutineUniform> {
    }

    interface TessEvaluationSubroutineUniform extends SubroutineUniform<TessEvaluationSubroutineUniform> {
    }

    interface GeometrySubroutineUniform extends SubroutineUniform<GeometrySubroutineUniform> {
    }

    interface FragmentSubroutineUniform extends SubroutineUniform<FragmentSubroutineUniform> {
    }

    interface ComputeSubroutineUniform extends SubroutineUniform<ComputeSubroutineUniform> {
    }
    //endregion

    //region Resource functions
    interface Named<T extends ProgramResource<T>> extends ProgramResource<T> {
        String getName();
    }

    interface Type<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getType();
    }

    interface ArraySize<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getArraySize();
    }

    interface Offset<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getOffset();
    }

    interface BlockIndex<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getBlockIndex();
    }

    interface ArrayStride<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getArrayStride();
    }

    interface MatrixStride<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getMatrixStride();
    }

    interface IsRowMajor<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isRowMajor();
    }

    interface AtomicCounterBufferIndex<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getAtomicCounterBufferIndex();
    }

    interface TextureBuffer<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getTextureBuffer();
    }

    interface BufferBinding<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getBufferBinding();
    }

    interface BufferDataSize<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getBufferDataSize();
    }

    interface NumActiveVariables<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getNumActiveVariables();
    }

    interface ActiveVariables<T extends ProgramResource<T>> extends ProgramResource<T> {
        int[] getActiveVariables();
    }

    interface ReferencedByVertexShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByVertexShader();
    }

    interface ReferencedByTessControlShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByTessControlShader();
    }

    interface ReferencedByTessEvaluationShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByTessEvaluationShader();
    }

    interface ReferencedByGeometryShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByGeometryShader();
    }

    interface ReferencedByFragmentShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByFragmentShader();
    }

    interface ReferencedByComputeShader<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isReferencedByComputeShader();
    }

    interface NumCompatibleSubroutines<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getNumCompatibleSubroutines();
    }

    interface CompatibleSubroutines<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getCompatibleSubroutines();
    }

    interface TopLevelArraySize<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isTopLevelArraySize();
    }

    interface TopLevelArrayStride<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getTopLevelArrayStride();
    }

    interface Location<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getLocation();
    }

    interface LocationIndex<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getLocationIndex();
    }

    interface IsPerPatch<T extends ProgramResource<T>> extends ProgramResource<T> {
        boolean isPerPatch();
    }

    interface LocationComponent<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getLocationComponent();
    }

    interface TransformFeedbackBufferIndex<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getTransformFeedbackBufferIndex();
    }

    interface TransformFeedbackBufferStride<T extends ProgramResource<T>> extends ProgramResource<T> {
        int getTransformFeedbackBufferStride();
    }
    //endregion

}
