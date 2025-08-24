package com.vanix.easygl.core.graphics.program;

import com.vanix.easygl.core.graphics.ProgramResource;

public interface GeometrySubroutineUniform extends
        ProgramResource.Named<GeometrySubroutineUniform>,
        ProgramResource.ArraySize<GeometrySubroutineUniform>,
        ProgramResource.NumCompatibleSubroutines<GeometrySubroutineUniform>,
        ProgramResource.CompatibleSubroutines<GeometrySubroutineUniform>,
        ProgramResource.Location<GeometrySubroutineUniform> {
}
