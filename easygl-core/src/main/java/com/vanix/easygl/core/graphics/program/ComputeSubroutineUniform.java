package com.vanix.easygl.core.graphics.program;

import com.vanix.easygl.core.graphics.ProgramResource;

public interface ComputeSubroutineUniform extends
        ProgramResource.Named<ComputeSubroutineUniform>,
        ProgramResource.ArraySize<ComputeSubroutineUniform>,
        ProgramResource.NumCompatibleSubroutines<ComputeSubroutineUniform>,
        ProgramResource.CompatibleSubroutines<ComputeSubroutineUniform>,
        ProgramResource.Location<ComputeSubroutineUniform> {
}
