package com.vanix.easygl.core.graphics;

public interface ProgramInterfaces {
    ProgramInterface.Uniform uniform();

    ProgramInterface.UniformBlock uniformBlock();

    ProgramInterface.AtomicCounterBuffer atomicCounterBuffer();

    ProgramInterface.ProgramInput programInput();

    ProgramInterface.ProgramOutput programOutput();

    ProgramInterface.VertexSubroutine vertexSubroutine();

    ProgramInterface.TessControlSubroutine tessControlSubroutine();

    ProgramInterface.TessEvaluationSubroutine tessEvaluationSubroutine();

    ProgramInterface.GeometrySubroutine geometrySubroutine();

    ProgramInterface.FragmentSubroutine fragmentSubroutine();

    ProgramInterface.ComputeSubroutine computeSubroutine();

    ProgramInterface.VertexSubroutineUniform vertexSubroutineUniform();

    ProgramInterface.TessControlSubroutineUniform tessControlSubroutineUniform();

    ProgramInterface.TessEvaluationSubroutineUniform tessEvaluationSubroutineUniform();

    ProgramInterface.GeometrySubroutineUniform geometrySubroutineUniform();

    ProgramInterface.FragmentSubroutineUniform fragmentSubroutineUniform();

    ProgramInterface.ComputeSubroutineUniform computeSubroutineUniform();

    ProgramInterface.TransformFeedbackVarying transformFeedbackVarying();

    ProgramInterface.BufferVariable bufferVariable();

    ProgramInterface.ShaderStorageBlock shaderStorageBlock();

    ProgramInterface.TransformFeedbackBuffer transformFeedbackBuffer();
}
