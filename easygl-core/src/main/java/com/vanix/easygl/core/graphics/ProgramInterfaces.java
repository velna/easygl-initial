package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

@Support(since = Version.GL43)
public abstract class ProgramInterfaces {
    private final Map<InterfaceType, ProgramInterface> interfaces = new EnumMap<>(InterfaceType.class);
    protected final Program program;

    public ProgramInterfaces(Program program) {
        this.program = program;
    }

    @SuppressWarnings("unchecked")
    private <T extends ProgramInterface> T getInterface(InterfaceType type) {
        return (T) interfaces.computeIfAbsent(type, t -> new ProgramInterfaceImpl(this, t.value, t.properties));
    }

    public ProgramInterface.Uniform uniform() {
        return getInterface(InterfaceType.Uniform);
    }

    public ProgramInterface.UniformBlock uniformBlock() {
        return getInterface(InterfaceType.UniformBlock);
    }

    public ProgramInterface.AtomicCounterBuffer atomicCounterBuffer() {
        return getInterface(InterfaceType.AtomicCounterBuffer);
    }

    public ProgramInterface.ProgramInput programInput() {
        return getInterface(InterfaceType.ProgramInput);
    }

    public ProgramInterface.ProgramOutput programOutput() {
        return getInterface(InterfaceType.ProgramOutput);
    }

    public ProgramInterface.VertexSubroutine vertexSubroutine() {
        return getInterface(InterfaceType.VertexSubroutine);
    }

    public ProgramInterface.TessControlSubroutine tessControlSubroutine() {
        return getInterface(InterfaceType.TessControlSubroutine);
    }

    public ProgramInterface.TessEvaluationSubroutine tessEvaluationSubroutine() {
        return getInterface(InterfaceType.TessEvaluationSubroutine);
    }

    public ProgramInterface.GeometrySubroutine geometrySubroutine() {
        return getInterface(InterfaceType.GeometrySubroutine);
    }

    public ProgramInterface.FragmentSubroutine fragmentSubroutine() {
        return getInterface(InterfaceType.FragmentSubroutine);
    }

    public ProgramInterface.ComputeSubroutine computeSubroutine() {
        return getInterface(InterfaceType.ComputeSubroutine);
    }

    public ProgramInterface.VertexSubroutineUniform vertexSubroutineUniform() {
        return getInterface(InterfaceType.VertexSubroutineUniform);
    }

    public ProgramInterface.TessControlSubroutineUniform tessControlSubroutineUniform() {
        return getInterface(InterfaceType.TessControlSubroutineUniform);
    }

    public ProgramInterface.TessEvaluationSubroutineUniform tessEvaluationSubroutineUniform() {
        return getInterface(InterfaceType.TessEvaluationSubroutineUniform);
    }

    public ProgramInterface.GeometrySubroutineUniform geometrySubroutineUniform() {
        return getInterface(InterfaceType.GeometrySubroutineUniform);
    }

    public ProgramInterface.FragmentSubroutineUniform fragmentSubroutineUniform() {
        return getInterface(InterfaceType.FragmentSubroutineUniform);
    }

    public ProgramInterface.ComputeSubroutineUniform computeSubroutineUniform() {
        return getInterface(InterfaceType.ComputeSubroutineUniform);
    }

    public ProgramInterface.TransformFeedbackVarying transformFeedbackVarying() {
        return getInterface(InterfaceType.TransformFeedbackVarying);
    }

    public ProgramInterface.BufferVariable bufferVariable() {
        return getInterface(InterfaceType.BufferVariable);
    }

    public ProgramInterface.ShaderStorageBlock shaderStorageBlock() {
        return getInterface(InterfaceType.ShaderStorageBlock);
    }

    public ProgramInterface.TransformFeedbackBuffer transformFeedbackBuffer() {
        return getInterface(InterfaceType.TransformFeedbackBuffer);
    }

    protected abstract Map<Program.PropertyKey, Integer> preload(ProgramInterface programInterface, int index, List<Program.PropertyKey> propertyKeys);

    protected abstract int getActiveResources(ProgramInterface programInterface);

    protected abstract int getMaxNameLength(ProgramInterface programInterface);

    protected abstract int getMaxNumActiveVariables(ProgramInterface programInterface);

    protected abstract int getMaxNumCompatibleSubroutines(ProgramInterface programInterface);

    protected abstract String getResourceName(ProgramInterface programInterface, int index);

    protected abstract int getResourceLocation(ProgramInterface programInterface, String name);

    protected abstract int getResourceIndex(ProgramInterface programInterface, String name);

    protected abstract int getResourceLocationIndex(ProgramInterface programInterface, String name);

    protected abstract int queryInt(ProgramInterface programInterface, Program.PropertyKey key, int index);

    protected abstract boolean queryBoolean(ProgramInterface programInterface, Program.PropertyKey key, int index);

    protected abstract int[] queryIntArray(ProgramInterface programInterface, Program.PropertyKey key, int index);

    enum InterfaceType implements IntEnum {
        Uniform("UNIFORM", ProgramInterface.Uniform.class),
        UniformBlock("UNIFORM_BLOCK", ProgramInterface.UniformBlock.class),
        AtomicCounterBuffer("ATOMIC_COUNTER_BUFFER", ProgramInterface.AtomicCounterBuffer.class),
        ProgramInput("PROGRAM_INPUT", ProgramInterface.ProgramInput.class),
        ProgramOutput("PROGRAM_OUTPUT", ProgramInterface.ProgramOutput.class),
        VertexSubroutine("VERTEX_SUBROUTINE", ProgramInterface.VertexSubroutine.class),
        TessControlSubroutine("TESS_CONTROL_SUBROUTINE", ProgramInterface.TessControlSubroutine.class),
        TessEvaluationSubroutine("TESS_EVALUATION_SUBROUTINE", ProgramInterface.TessEvaluationSubroutine.class),
        GeometrySubroutine("GEOMETRY_SUBROUTINE", ProgramInterface.GeometrySubroutine.class),
        FragmentSubroutine("FRAGMENT_SUBROUTINE", ProgramInterface.FragmentSubroutine.class),
        ComputeSubroutine("COMPUTE_SUBROUTINE", ProgramInterface.ComputeSubroutine.class),
        VertexSubroutineUniform("VERTEX_SUBROUTINE_UNIFORM", ProgramInterface.VertexSubroutineUniform.class),
        TessControlSubroutineUniform("TESS_CONTROL_SUBROUTINE_UNIFORM", ProgramInterface.TessControlSubroutineUniform.class),
        TessEvaluationSubroutineUniform("TESS_EVALUATION_SUBROUTINE_UNIFORM", ProgramInterface.TessEvaluationSubroutineUniform.class),
        GeometrySubroutineUniform("GEOMETRY_SUBROUTINE_UNIFORM", ProgramInterface.GeometrySubroutineUniform.class),
        FragmentSubroutineUniform("FRAGMENT_SUBROUTINE_UNIFORM", ProgramInterface.FragmentSubroutineUniform.class),
        ComputeSubroutineUniform("COMPUTE_SUBROUTINE_UNIFORM", ProgramInterface.ComputeSubroutineUniform.class),
        TransformFeedbackVarying("TRANSFORM_FEEDBACK_VARYING", ProgramInterface.TransformFeedbackVarying.class),
        BufferVariable("BUFFER_VARIABLE", ProgramInterface.BufferVariable.class),
        ShaderStorageBlock("SHADER_STORAGE_BLOCK", ProgramInterface.ShaderStorageBlock.class),
        TransformFeedbackBuffer("TRANSFORM_FEEDBACK_BUFFER", ProgramInterface.TransformFeedbackBuffer.class);

        final int value;
        final BitSet<Program.PropertyKey> properties = BitSet.of((ToIntFunction<Program.PropertyKey>) Program.PropertyKey::ordinal);

        InterfaceType(String id, Class<?> type) {
            this.value = MetaSystem.Graphics.queryInt(id);
            for (var ifc : type.getInterfaces()) {
                if (ProgramInterface.Property.class.isAssignableFrom(ifc)) {
                    var propertyKey = Program.PropertyKey.valueOf(ifc.getSimpleName());
                    properties.add(propertyKey);
                }
            }
        }

        @Override
        public int value() {
            return value;
        }
    }

}
