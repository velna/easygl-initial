package com.vanix.easygl.core.graphics.program;

import com.vanix.easygl.core.graphics.ProgramResource;

public interface VertexSubroutineUniform extends
        ProgramResource.Named<VertexSubroutineUniform>,
        ProgramResource.ArraySize<VertexSubroutineUniform>,
        ProgramResource.NumCompatibleSubroutines<VertexSubroutineUniform>,
        ProgramResource.CompatibleSubroutines<VertexSubroutineUniform>,
        ProgramResource.Location<VertexSubroutineUniform> {
}
