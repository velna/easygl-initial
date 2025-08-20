package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.Support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

@Support(since = Version.GL43)
final class ProgramInterfaceImpl extends SimpleIntEnum implements
        ProgramInterface.Uniform,
        ProgramInterface.UniformBlock,
        ProgramInterface.AtomicCounterBuffer,
        ProgramInterface.ProgramInput,
        ProgramInterface.ProgramOutput,
        ProgramInterface.VertexSubroutine,
        ProgramInterface.TessControlSubroutine,
        ProgramInterface.TessEvaluationSubroutine,
        ProgramInterface.GeometrySubroutine,
        ProgramInterface.FragmentSubroutine,
        ProgramInterface.ComputeSubroutine,
        ProgramInterface.VertexSubroutineUniform,
        ProgramInterface.TessControlSubroutineUniform,
        ProgramInterface.TessEvaluationSubroutineUniform,
        ProgramInterface.GeometrySubroutineUniform,
        ProgramInterface.FragmentSubroutineUniform,
        ProgramInterface.ComputeSubroutineUniform,
        ProgramInterface.TransformFeedbackVarying,
        ProgramInterface.BufferVariable,
        ProgramInterface.ShaderStorageBlock,
        ProgramInterface.TransformFeedbackBuffer {
    private final BitSet<Program.PropertyKey> properties;
    private final ProgramInterfaces programInterfaces;
    private final Map<Program.PropertyKey, Integer> preloadValues;

    public ProgramInterfaceImpl(ProgramInterfaces programInterfaces, int value, BitSet<Program.PropertyKey> properties) {
        super(value);
        this.properties = properties;
        this.programInterfaces = programInterfaces;
        preloadValues = null;
    }

    public ProgramInterfaceImpl(ProgramInterface programInterface, int index, Program.PropertyKey... keys) {
        super(programInterface.value());
        this.properties = BitSet.of((ToIntFunction<Program.PropertyKey>) Program.PropertyKey::ordinal);
        ProgramInterfaceImpl ifc = (ProgramInterfaceImpl) programInterface;
        this.programInterfaces = ifc.programInterfaces;
        List<Program.PropertyKey> keyList = new ArrayList<>();
        for (var key : keys) {
            if (ifc.properties.contains(key)) {
                properties.add(key);
                keyList.add(key);
            }
        }
        preloadValues = programInterfaces.preload(this, index, keyList);
    }

    @Override
    public int getActiveResources() {
        return programInterfaces.getActiveResources(this);
    }

    @Override
    public int getMaxNameLength() {
        return programInterfaces.getMaxNameLength(this);
    }

    @Override
    public String getResourceName(int index) {
        return programInterfaces.getResourceName(this, index);
    }

    @Override
    public int getResourceLocation(String name) {
        return programInterfaces.getResourceLocation(this, name);
    }

    @Override
    public int getResourceIndex(String name) {
        return programInterfaces.getResourceIndex(this, name);
    }

    @Override
    public int getResourceLocationIndex(String name) {
        return programInterfaces.getResourceLocationIndex(this, name);
    }

    @Override
    public int getMaxNumActiveVariables() {
        return programInterfaces.getMaxNumActiveVariables(this);
    }

    @Override
    public int getMaxNumCompatibleSubroutines() {
        return programInterfaces.getMaxNumCompatibleSubroutines(this);
    }

    private int queryInt(Program.PropertyKey key, int index) {
        if (preloadValues != null && properties.contains(key)) {
            return preloadValues.get(key);
        } else {
            return programInterfaces.queryInt(this, key, index);
        }
    }

    private boolean queryBoolean(Program.PropertyKey key, int index) {
        if (preloadValues != null && properties.contains(key)) {
            return preloadValues.get(key) != 0;
        } else {
            return programInterfaces.queryBoolean(this, key, index);
        }
    }

    @Override
    public int getNameLength(int index) {
        return queryInt(Program.PropertyKey.NameLength, index);
    }

    @Override
    public int getType(int index) {
        return queryInt(Program.PropertyKey.Type, index);
    }

    @Override
    public int getArraySize(int index) {
        return queryInt(Program.PropertyKey.ArraySize, index);
    }

    @Override
    public int getOffset(int index) {
        return queryInt(Program.PropertyKey.Offset, index);
    }

    @Override
    public int getBlockIndex(int index) {
        return queryInt(Program.PropertyKey.BlockIndex, index);
    }

    @Override
    public int getArrayStride(int index) {
        return queryInt(Program.PropertyKey.ArrayStride, index);
    }

    @Override
    public int getMatrixStride(int index) {
        return queryInt(Program.PropertyKey.MatrixStride, index);
    }

    @Override
    public boolean isRowMajor(int index) {
        return queryBoolean(Program.PropertyKey.IsRowMajor, index);
    }

    @Override
    public int getAtomicCounterBufferIndex(int index) {
        return queryInt(Program.PropertyKey.AtomicCounterBufferIndex, index);
    }

    @Override
    public int getBufferBinding(int index) {
        return queryInt(Program.PropertyKey.BufferBinding, index);
    }

    @Override
    public int getBufferDataSize(int index) {
        return queryInt(Program.PropertyKey.BufferDataSize, index);
    }

    @Override
    public int getNumActiveVariables(int index) {
        return queryInt(Program.PropertyKey.NumActiveVariables, index);
    }

    @Override
    public int[] getActiveVariables(int index) {
        return programInterfaces.queryIntArray(this, Program.PropertyKey.ActiveVariables, index);
    }

    @Override
    public boolean isReferencedByVertexShader(int index) {
        return queryBoolean(Program.PropertyKey.ReferencedByVertexShader, index);
    }

    @Override
    public boolean isReferencedByTessControlShader(int index) {
        return queryBoolean(Program.PropertyKey.ReferencedByTessControlShader, index);
    }

    @Override
    public boolean isReferencedByTessEvaluationShader(int index) {
        return queryBoolean(Program.PropertyKey.ReferencedByTessEvaluationShader, index);
    }

    @Override
    public boolean isReferencedByGeometryShader(int index) {
        return queryBoolean(Program.PropertyKey.ReferencedByGeometryShader, index);
    }

    @Override
    public boolean isReferencedByFragmentShader(int index) {
        return queryBoolean(Program.PropertyKey.ReferencedByFragmentShader, index);
    }

    @Override
    public boolean isReferencedByComputeShader(int index) {
        return queryBoolean(Program.PropertyKey.ReferencedByComputeShader, index);
    }

    @Override
    public int getNumCompatibleSubroutines(int index) {
        return queryInt(Program.PropertyKey.NumCompatibleSubroutines, index);
    }

    @Override
    public int getCompatibleSubroutines(int index) {
        return queryInt(Program.PropertyKey.CompatibleSubroutines, index);
    }

    @Override
    public boolean isTopLevelArraySize(int index) {
        return queryBoolean(Program.PropertyKey.TopLevelArraySize, index);
    }

    @Override
    public int getTopLevelArrayStride(int index) {
        return queryInt(Program.PropertyKey.TopLevelArrayStride, index);
    }

    @Override
    public int getLocation(int index) {
        return queryInt(Program.PropertyKey.Location, index);
    }

    @Override
    public int getLocationIndex(int index) {
        return queryInt(Program.PropertyKey.LocationIndex, index);
    }

    @Override
    public boolean isPerPatch(int index) {
        return queryBoolean(Program.PropertyKey.IsPerPatch, index);
    }

    @Override
    public int getLocationComponent(int index) {
        return queryInt(Program.PropertyKey.LocationComponent, index);
    }

    @Override
    public int getTransformFeedbackBufferIndex(int index) {
        return queryInt(Program.PropertyKey.TransformFeedbackBufferIndex, index);
    }

    @Override
    public int getTransformFeedbackBufferStride(int index) {
        return queryInt(Program.PropertyKey.TransformFeedbackBufferStride, index);
    }

}
