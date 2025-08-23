package com.vanix.easygl.opengl;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.graphics.*;

import java.util.EnumMap;
import java.util.Map;

@Support(since = Version.GL43)
public class GlProgramInterfaces implements ProgramInterfaces {
    protected final Program program;
    private final Map<GlProgramInterfaceType, ProgramInterface<?>> interfaces = new EnumMap<>(GlProgramInterfaceType.class);

    protected GlProgramInterfaces(Program program) {
        this.program = program;
    }

    @SuppressWarnings("unchecked")
    private <R extends ProgramResource<R>, T extends ProgramInterface<R>> T getInterface(GlProgramInterfaceType type) {
        return (T) interfaces.computeIfAbsent(type, t -> t.factory.apply(program));
    }

    @Override
    public ProgramInterface.Uniform uniform() {
        return getInterface(GlProgramInterfaceType.Uniform);
    }

    @Override
    public ProgramInterface.UniformBlock uniformBlock() {
        return getInterface(GlProgramInterfaceType.UniformBlock);
    }

    @Override
    public ProgramInterface.AtomicCounterBuffer atomicCounterBuffer() {
        return getInterface(GlProgramInterfaceType.AtomicCounterBuffer);
    }

    @Override
    public ProgramInterface.ProgramInput programInput() {
        return getInterface(GlProgramInterfaceType.ProgramInput);
    }

    @Override
    public ProgramInterface.ProgramOutput programOutput() {
        return getInterface(GlProgramInterfaceType.ProgramOutput);
    }

    @Override
    public ProgramInterface.VertexSubroutine vertexSubroutine() {
        return getInterface(GlProgramInterfaceType.VertexSubroutine);
    }

    @Override
    public ProgramInterface.TessControlSubroutine tessControlSubroutine() {
        return getInterface(GlProgramInterfaceType.TessControlSubroutine);
    }

    @Override
    public ProgramInterface.TessEvaluationSubroutine tessEvaluationSubroutine() {
        return getInterface(GlProgramInterfaceType.TessEvaluationSubroutine);
    }

    @Override
    public ProgramInterface.GeometrySubroutine geometrySubroutine() {
        return getInterface(GlProgramInterfaceType.GeometrySubroutine);
    }

    @Override
    public ProgramInterface.FragmentSubroutine fragmentSubroutine() {
        return getInterface(GlProgramInterfaceType.FragmentSubroutine);
    }

    @Override
    public ProgramInterface.ComputeSubroutine computeSubroutine() {
        return getInterface(GlProgramInterfaceType.ComputeSubroutine);
    }

    @Override
    public ProgramInterface.VertexSubroutineUniform vertexSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    public ProgramInterface.TessControlSubroutineUniform tessControlSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.TessControlSubroutineUniform);
    }

    @Override
    public ProgramInterface.TessEvaluationSubroutineUniform tessEvaluationSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.TessEvaluationSubroutineUniform);
    }

    @Override
    public ProgramInterface.GeometrySubroutineUniform geometrySubroutineUniform() {
        return getInterface(GlProgramInterfaceType.GeometrySubroutineUniform);
    }

    @Override
    public ProgramInterface.FragmentSubroutineUniform fragmentSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.FragmentSubroutineUniform);
    }

    @Override
    public ProgramInterface.ComputeSubroutineUniform computeSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.ComputeSubroutineUniform);
    }

    @Override
    public ProgramInterface.TransformFeedbackVarying transformFeedbackVarying() {
        return getInterface(GlProgramInterfaceType.TransformFeedbackVarying);
    }

    @Override
    public ProgramInterface.BufferVariable bufferVariable() {
        return getInterface(GlProgramInterfaceType.BufferVariable);
    }

    @Override
    public ProgramInterface.ShaderStorageBlock shaderStorageBlock() {
        return getInterface(GlProgramInterfaceType.ShaderStorageBlock);
    }

    @Override
    public ProgramInterface.TransformFeedbackBuffer transformFeedbackBuffer() {
        return getInterface(GlProgramInterfaceType.TransformFeedbackBuffer);
    }
}
