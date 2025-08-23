package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Support;

import java.util.List;

@Support(since = Version.GL43)
public interface ProgramInterface<T extends ProgramResource<T>> {

    int getActiveResources();

    List<T> getResources();

    //region Real Interfaces
    interface Uniform extends Named<ProgramResource.Uniform> {
    }

    interface UniformBlock extends Variable<ProgramResource.UniformBlock>, Named<ProgramResource.UniformBlock> {
    }

    interface AtomicCounterBuffer extends Variable<ProgramResource.AtomicCounterBuffer> {
    }

    interface ProgramInput extends Named<ProgramResource.ProgramInput> {
    }

    interface ProgramOutput extends Named<ProgramResource.ProgramOutput> {
    }

    interface VertexSubroutine extends Named<ProgramResource.VertexSubroutine> {
    }

    interface TessControlSubroutine extends Named<ProgramResource.TessControlSubroutine> {
    }

    interface TessEvaluationSubroutine extends Named<ProgramResource.TessEvaluationSubroutine> {
    }

    interface GeometrySubroutine extends Named<ProgramResource.GeometrySubroutine> {
    }

    interface FragmentSubroutine extends Named<ProgramResource.FragmentSubroutine> {
    }

    interface ComputeSubroutine extends Named<ProgramResource.ComputeSubroutine> {
    }

    interface VertexSubroutineUniform extends SubroutineUniform<ProgramResource.VertexSubroutineUniform> {
    }

    interface TessControlSubroutineUniform extends SubroutineUniform<ProgramResource.TessControlSubroutineUniform> {
    }

    interface TessEvaluationSubroutineUniform extends SubroutineUniform<ProgramResource.TessEvaluationSubroutineUniform> {
    }

    interface GeometrySubroutineUniform extends SubroutineUniform<ProgramResource.GeometrySubroutineUniform> {
    }

    interface FragmentSubroutineUniform extends SubroutineUniform<ProgramResource.FragmentSubroutineUniform> {
    }

    interface ComputeSubroutineUniform extends SubroutineUniform<ProgramResource.ComputeSubroutineUniform> {
    }

    interface TransformFeedbackVarying extends Named<ProgramResource.TransformFeedbackVarying> {
    }

    interface BufferVariable extends Named<ProgramResource.BufferVariable> {
    }

    interface ShaderStorageBlock extends Variable<ProgramResource.ShaderStorageBlock>, Named<ProgramResource.ShaderStorageBlock> {
    }

    interface TransformFeedbackBuffer extends Variable<ProgramResource.TransformFeedbackBuffer>, Named<ProgramResource.TransformFeedbackBuffer> {
    }
    //endregion

    //region Base interfaces
    interface Named<T extends ProgramResource<T>> extends ProgramInterface<T> {
        T getResource(String name);
    }

    interface Variable<T extends ProgramResource<T>> extends ProgramInterface<T> {
        int getMaxNumActiveVariables();
    }

    interface SubroutineUniform<T extends ProgramResource<T>> extends Named<T> {
        int getMaxNumCompatibleSubroutines();
    }
    //endregion

}
