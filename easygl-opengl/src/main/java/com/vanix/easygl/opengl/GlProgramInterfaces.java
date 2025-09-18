package com.vanix.easygl.opengl;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.graphics.Program;
import com.vanix.easygl.core.graphics.ProgramInterfaces;
import com.vanix.easygl.core.graphics.ProgramResource;
import com.vanix.easygl.core.graphics.Version;
import com.vanix.easygl.core.graphics.program.*;
import com.vanix.easygl.opengl.program.BaseInterface;

import java.util.EnumMap;
import java.util.Map;

@Support(since = Version.GL43)
public class GlProgramInterfaces implements ProgramInterfaces {
    protected final Program program;
    private final Map<GlProgramInterfaceType, BaseInterface<?>> interfaces = new EnumMap<>(GlProgramInterfaceType.class);

    protected GlProgramInterfaces(Program program) {
        this.program = program;
    }

    void invalidate() {
        interfaces.values().forEach(BaseInterface::invalidate);
    }

    @SuppressWarnings("unchecked")
    private <R extends ProgramResource<R>, T extends BaseInterface<R>> T getInterface(GlProgramInterfaceType type) {
        return (T) interfaces.computeIfAbsent(type, t -> t.factory.apply(program));
    }

    @Override
    public <B> B bindResources(B bean) {
        for (var interfaceType : GlProgramInterfaceType.values()) {
            getInterface(interfaceType).bindResources(bean);
        }
        return bean;
    }

    @Override
    public UniformInterface uniform() {
        return getInterface(GlProgramInterfaceType.Uniform);
    }

    @Override
    public UniformBlockInterface uniformBlock() {
        return getInterface(GlProgramInterfaceType.UniformBlock);
    }

    @Override
    public AtomicCounterBufferInterface atomicCounterBuffer() {
        return getInterface(GlProgramInterfaceType.AtomicCounterBuffer);
    }

    @Override
    public ProgramInputInterface programInput() {
        return getInterface(GlProgramInterfaceType.ProgramInput);
    }

    @Override
    public ProgramOutputInterface programOutput() {
        return getInterface(GlProgramInterfaceType.ProgramOutput);
    }

    @Override
    public VertexSubroutineInterface vertexSubroutine() {
        return getInterface(GlProgramInterfaceType.VertexSubroutine);
    }

    @Override
    public TessControlSubroutineInterface tessControlSubroutine() {
        return getInterface(GlProgramInterfaceType.TessControlSubroutine);
    }

    @Override
    public TessEvaluationSubroutineInterface tessEvaluationSubroutine() {
        return getInterface(GlProgramInterfaceType.TessEvaluationSubroutine);
    }

    @Override
    public GeometrySubroutineInterface geometrySubroutine() {
        return getInterface(GlProgramInterfaceType.GeometrySubroutine);
    }

    @Override
    public FragmentSubroutineInterface fragmentSubroutine() {
        return getInterface(GlProgramInterfaceType.FragmentSubroutine);
    }

    @Override
    public ComputeSubroutineInterface computeSubroutine() {
        return getInterface(GlProgramInterfaceType.ComputeSubroutine);
    }

    @Override
    public VertexSubroutineUniformInterface vertexSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.VertexSubroutineUniform);
    }

    @Override
    public TessControlSubroutineUniformInterface tessControlSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.TessControlSubroutineUniform);
    }

    @Override
    public TessEvaluationSubroutineUniformInterface tessEvaluationSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.TessEvaluationSubroutineUniform);
    }

    @Override
    public GeometrySubroutineUniformInterface geometrySubroutineUniform() {
        return getInterface(GlProgramInterfaceType.GeometrySubroutineUniform);
    }

    @Override
    public FragmentSubroutineUniformInterface fragmentSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.FragmentSubroutineUniform);
    }

    @Override
    public ComputeSubroutineUniformInterface computeSubroutineUniform() {
        return getInterface(GlProgramInterfaceType.ComputeSubroutineUniform);
    }

    @Override
    public TransformFeedbackVaryingInterface transformFeedbackVarying() {
        return getInterface(GlProgramInterfaceType.TransformFeedbackVarying);
    }

    @Override
    public BufferVariableInterface bufferVariable() {
        return getInterface(GlProgramInterfaceType.BufferVariable);
    }

    @Override
    public ShaderStorageBlockInterface shaderStorageBlock() {
        return getInterface(GlProgramInterfaceType.ShaderStorageBlock);
    }

    @Override
    public TransformFeedbackBufferInterface transformFeedbackBuffer() {
        return getInterface(GlProgramInterfaceType.TransformFeedbackBuffer);
    }
}
