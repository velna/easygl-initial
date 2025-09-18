package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.program.*;

public interface ProgramInterfaces {

    <B> B bindResources(B bean);

    UniformInterface uniform();

    UniformBlockInterface uniformBlock();

    AtomicCounterBufferInterface atomicCounterBuffer();

    ProgramInputInterface programInput();

    ProgramOutputInterface programOutput();

    VertexSubroutineInterface vertexSubroutine();

    TessControlSubroutineInterface tessControlSubroutine();

    TessEvaluationSubroutineInterface tessEvaluationSubroutine();

    GeometrySubroutineInterface geometrySubroutine();

    FragmentSubroutineInterface fragmentSubroutine();

    ComputeSubroutineInterface computeSubroutine();

    VertexSubroutineUniformInterface vertexSubroutineUniform();

    TessControlSubroutineUniformInterface tessControlSubroutineUniform();

    TessEvaluationSubroutineUniformInterface tessEvaluationSubroutineUniform();

    GeometrySubroutineUniformInterface geometrySubroutineUniform();

    FragmentSubroutineUniformInterface fragmentSubroutineUniform();

    ComputeSubroutineUniformInterface computeSubroutineUniform();

    TransformFeedbackVaryingInterface transformFeedbackVarying();

    BufferVariableInterface bufferVariable();

    ShaderStorageBlockInterface shaderStorageBlock();

    TransformFeedbackBufferInterface transformFeedbackBuffer();
}
