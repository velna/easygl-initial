package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Support;

@Support(since = Version.GL43)
public sealed interface ProgramInterface extends IntEnum permits
        ProgramInterface.Uniform,
        ProgramInterface.UniformBlock,
        ProgramInterface.AtomicCounterBuffer,
        ProgramInterface.ProgramInput,
        ProgramInterface.ProgramOutput,
        ProgramInterface.VertexSubroutine,
        ProgramInterface.TessControlSubroutine,
        ProgramInterface.TessEvaluationSubroutine,
        ProgramInterface.VertexSubroutineUniform,
        ProgramInterface.TessControlSubroutineUniform,
        ProgramInterface.TessEvaluationSubroutineUniform,
        ProgramInterface.GeometrySubroutineUniform,
        ProgramInterface.FragmentSubroutineUniform,
        ProgramInterface.ComputeSubroutineUniform,
        ProgramInterface.GeometrySubroutine,
        ProgramInterface.FragmentSubroutine,
        ProgramInterface.ComputeSubroutine,
        ProgramInterface.ShaderStorageBlock,
        ProgramInterface.TransformFeedbackBuffer,
        ProgramInterface.TransformFeedbackVarying,
        ProgramInterface.BufferVariable {

    interface Resource {
        int getActiveResources();
    }

    interface NamedResource extends Resource {
        int getMaxNameLength();

        String getResourceName(int index);

        int getResourceIndex(String name);
    }

    interface LocatableResource extends Resource {
        int getResourceLocation(String name);
    }

    interface VariableResource extends Resource {
        int getMaxNumActiveVariables();
    }

    interface SubroutineUniformResource extends NamedResource, LocatableResource {
        int getMaxNumCompatibleSubroutines();
    }


    sealed interface Uniform extends ProgramInterface, NamedResource, LocatableResource,
            Property.NameLength,
            Property.Type,
            Property.ArraySize,
            Property.Offset,
            Property.BlockIndex,
            Property.ArrayStride,
            Property.MatrixStride,
            Property.IsRowMajor,
            Property.AtomicCounterBufferIndex,
            Property.ReferencedByTessEvaluationShader,
            Property.ReferencedByComputeShader,
            Property.ReferencedByVertexShader,
            Property.ReferencedByGeometryShader,
            Property.ReferencedByFragmentShader,
            Property.ReferencedByTessControlShader,
            Property.Location
            permits ProgramInterfaceImpl {
    }

    sealed interface UniformBlock extends ProgramInterface, VariableResource, NamedResource,
            Property.BufferBinding,
            Property.BufferDataSize,
            Property.NumActiveVariables,
            Property.ActiveVariables,
            Property.ReferencedByTessEvaluationShader,
            Property.ReferencedByComputeShader,
            Property.ReferencedByVertexShader,
            Property.ReferencedByGeometryShader,
            Property.ReferencedByFragmentShader,
            Property.ReferencedByTessControlShader
            permits ProgramInterfaceImpl {
    }

    sealed interface AtomicCounterBuffer extends ProgramInterface, VariableResource,
            Property.BufferBinding,
            Property.BufferDataSize,
            Property.NumActiveVariables,
            Property.ActiveVariables
            permits ProgramInterfaceImpl {
    }

    sealed interface ProgramInput extends ProgramInterface, NamedResource, LocatableResource,
            Property.Type,
            Property.ArraySize,
            Property.ReferencedByTessEvaluationShader,
            Property.ReferencedByComputeShader,
            Property.ReferencedByVertexShader,
            Property.ReferencedByGeometryShader,
            Property.ReferencedByFragmentShader,
            Property.ReferencedByTessControlShader,
            Property.Location,
            Property.IsPerPatch,
            Property.LocationComponent
            permits ProgramInterfaceImpl {
    }

    sealed interface ProgramOutput extends ProgramInterface, NamedResource, LocatableResource,
            Property.Type,
            Property.ArraySize,
            Property.ReferencedByTessEvaluationShader,
            Property.ReferencedByComputeShader,
            Property.ReferencedByVertexShader,
            Property.ReferencedByGeometryShader,
            Property.ReferencedByFragmentShader,
            Property.ReferencedByTessControlShader,
            Property.Location,
            Property.LocationIndex,
            Property.IsPerPatch,
            Property.LocationComponent
            permits ProgramInterfaceImpl {
        int getResourceLocationIndex(String name);
    }

    sealed interface VertexSubroutine extends ProgramInterface, NamedResource
            permits ProgramInterfaceImpl {
    }

    sealed interface TessControlSubroutine extends ProgramInterface, NamedResource
            permits ProgramInterfaceImpl {
    }

    sealed interface TessEvaluationSubroutine extends ProgramInterface, NamedResource
            permits ProgramInterfaceImpl {
    }

    sealed interface GeometrySubroutine extends ProgramInterface, NamedResource
            permits ProgramInterfaceImpl {
    }

    sealed interface FragmentSubroutine extends ProgramInterface, NamedResource
            permits ProgramInterfaceImpl {
    }

    sealed interface ComputeSubroutine extends ProgramInterface, NamedResource
            permits ProgramInterfaceImpl {
    }

    sealed interface VertexSubroutineUniform extends ProgramInterface, SubroutineUniformResource,
            Property.ArraySize,
            Property.NumCompatibleSubroutines,
            Property.CompatibleSubroutines,
            Property.Location
            permits ProgramInterfaceImpl {
    }

    sealed interface TessControlSubroutineUniform extends ProgramInterface, SubroutineUniformResource,
            Property.ArraySize,
            Property.NumCompatibleSubroutines,
            Property.CompatibleSubroutines,
            Property.Location
            permits ProgramInterfaceImpl {
    }

    sealed interface TessEvaluationSubroutineUniform extends ProgramInterface, SubroutineUniformResource,
            Property.ArraySize,
            Property.NumCompatibleSubroutines,
            Property.CompatibleSubroutines,
            Property.Location
            permits ProgramInterfaceImpl {
    }

    sealed interface GeometrySubroutineUniform extends ProgramInterface, SubroutineUniformResource,
            Property.ArraySize,
            Property.NumCompatibleSubroutines,
            Property.CompatibleSubroutines,
            Property.Location
            permits ProgramInterfaceImpl {
    }

    sealed interface FragmentSubroutineUniform extends ProgramInterface, SubroutineUniformResource,
            Property.ArraySize,
            Property.NumCompatibleSubroutines,
            Property.CompatibleSubroutines,
            Property.Location
            permits ProgramInterfaceImpl {
    }

    sealed interface ComputeSubroutineUniform extends ProgramInterface, SubroutineUniformResource,
            Property.ArraySize,
            Property.NumCompatibleSubroutines,
            Property.CompatibleSubroutines,
            Property.Location
            permits ProgramInterfaceImpl {
    }

    sealed interface TransformFeedbackVarying extends ProgramInterface, NamedResource,
            Property.Type,
            Property.ArraySize,
            Property.Offset,
            Property.TransformFeedbackBufferIndex
            permits ProgramInterfaceImpl {
    }

    sealed interface BufferVariable extends ProgramInterface, NamedResource,
            Property.Type,
            Property.ArraySize,
            Property.Offset,
            Property.BlockIndex,
            Property.ArrayStride,
            Property.MatrixStride,
            Property.IsRowMajor,
            Property.ReferencedByTessEvaluationShader,
            Property.ReferencedByComputeShader,
            Property.ReferencedByVertexShader,
            Property.ReferencedByGeometryShader,
            Property.ReferencedByFragmentShader,
            Property.ReferencedByTessControlShader,
            Property.TopLevelArraySize,
            Property.TopLevelArrayStride
            permits ProgramInterfaceImpl {
    }

    sealed interface ShaderStorageBlock extends ProgramInterface, VariableResource, NamedResource,
            Property.BufferBinding,
            Property.BufferDataSize,
            Property.NumActiveVariables,
            Property.ActiveVariables,
            Property.ReferencedByTessEvaluationShader,
            Property.ReferencedByComputeShader,
            Property.ReferencedByVertexShader,
            Property.ReferencedByGeometryShader,
            Property.ReferencedByFragmentShader,
            Property.ReferencedByTessControlShader
            permits ProgramInterfaceImpl {
    }

    sealed interface TransformFeedbackBuffer extends ProgramInterface, VariableResource, LocatableResource,
            Property.NameLength,
            Property.BufferBinding,
            Property.NumActiveVariables,
            Property.ActiveVariables,
            Property.TransformFeedbackBufferIndex,
            Property.TransformFeedbackBufferStride
            permits ProgramInterfaceImpl {
    }

    interface Property extends IntEnum {

        interface NameLength extends Property {
            int getNameLength(int index);
        }

        interface Type extends Property {
            int getType(int index);
        }

        interface ArraySize extends Property {
            int getArraySize(int index);
        }

        interface Offset extends Property {
            int getOffset(int index);
        }

        interface BlockIndex extends Property {
            int getBlockIndex(int index);
        }

        interface ArrayStride extends Property {
            int getArrayStride(int index);
        }

        interface MatrixStride extends Property {
            int getMatrixStride(int index);
        }

        interface IsRowMajor extends Property {
            boolean isRowMajor(int index);
        }

        interface AtomicCounterBufferIndex extends Property {
            int getAtomicCounterBufferIndex(int index);
        }

        interface TextureBuffer extends Property {
            int getTextureBuffer(int index);
        }

        interface BufferBinding extends Property {
            int getBufferBinding(int index);
        }

        interface BufferDataSize extends Property {
            int getBufferDataSize(int index);
        }

        interface NumActiveVariables extends Property {
            int getNumActiveVariables(int index);
        }

        interface ActiveVariables extends Property {
            int[] getActiveVariables(int index);
        }

        interface ReferencedByVertexShader extends Property {
            boolean isReferencedByVertexShader(int index);
        }

        interface ReferencedByTessControlShader extends Property {
            boolean isReferencedByTessControlShader(int index);
        }

        interface ReferencedByTessEvaluationShader extends Property {
            boolean isReferencedByTessEvaluationShader(int index);
        }

        interface ReferencedByGeometryShader extends Property {
            boolean isReferencedByGeometryShader(int index);
        }

        interface ReferencedByFragmentShader extends Property {
            boolean isReferencedByFragmentShader(int index);
        }

        interface ReferencedByComputeShader extends Property {
            boolean isReferencedByComputeShader(int index);
        }

        interface NumCompatibleSubroutines extends Property {
            int getNumCompatibleSubroutines(int index);
        }

        interface CompatibleSubroutines extends Property {
            int getCompatibleSubroutines(int index);
        }

        interface TopLevelArraySize extends Property {
            boolean isTopLevelArraySize(int index);
        }

        interface TopLevelArrayStride extends Property {
            int getTopLevelArrayStride(int index);
        }

        interface Location extends Property {
            int getLocation(int index);
        }

        interface LocationIndex extends Property {
            int getLocationIndex(int index);
        }

        interface IsPerPatch extends Property {
            boolean isPerPatch(int index);
        }

        interface LocationComponent extends Property {
            int getLocationComponent(int index);
        }

        interface TransformFeedbackBufferIndex extends Property {
            int getTransformFeedbackBufferIndex(int index);
        }

        interface TransformFeedbackBufferStride extends Property {
            int getTransformFeedbackBufferStride(int index);
        }

    }

}
