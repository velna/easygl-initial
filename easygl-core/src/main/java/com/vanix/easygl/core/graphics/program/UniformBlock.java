package com.vanix.easygl.core.graphics.program;

import com.vanix.easygl.core.graphics.ProgramResource;

public interface UniformBlock extends
        ProgramResource.Named<UniformBlock>,
        ProgramResource.BufferBinding<UniformBlock>,
        ProgramResource.BufferDataSize<UniformBlock>,
        ProgramResource.NumActiveVariables<UniformBlock>,
        ProgramResource.ActiveVariables<UniformBlock>,
        ProgramResource.ReferencedByTessEvaluationShader<UniformBlock>,
        ProgramResource.ReferencedByComputeShader<UniformBlock>,
        ProgramResource.ReferencedByVertexShader<UniformBlock>,
        ProgramResource.ReferencedByGeometryShader<UniformBlock>,
        ProgramResource.ReferencedByFragmentShader<UniformBlock>,
        ProgramResource.ReferencedByTessControlShader<UniformBlock> {
}
