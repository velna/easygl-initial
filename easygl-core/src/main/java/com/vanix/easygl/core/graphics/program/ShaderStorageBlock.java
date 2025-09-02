package com.vanix.easygl.core.graphics.program;

import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.ProgramResource;

public interface ShaderStorageBlock extends
        ProgramResource.Named<ShaderStorageBlock>,
        ProgramResource.BufferBinding<ShaderStorageBlock>,
        ProgramResource.BufferDataSize<ShaderStorageBlock>,
        ProgramResource.NumActiveVariables<ShaderStorageBlock>,
        ProgramResource.ActiveVariables<ShaderStorageBlock>,
        ProgramResource.ReferencedByTessEvaluationShader<ShaderStorageBlock>,
        ProgramResource.ReferencedByComputeShader<ShaderStorageBlock>,
        ProgramResource.ReferencedByVertexShader<ShaderStorageBlock>,
        ProgramResource.ReferencedByGeometryShader<ShaderStorageBlock>,
        ProgramResource.ReferencedByFragmentShader<ShaderStorageBlock>,
        ProgramResource.ReferencedByTessControlShader<ShaderStorageBlock> {
    ShaderStorageBlock bind(Buffer.BindingPoint bindingPoint);
}
