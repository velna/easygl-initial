package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;

public sealed interface ProgramAttribute permits ProgramAttribute.Bool, ProgramAttribute.Int {
    Bool DeleteStatus = ProgramAttributeImpl.DeleteStatus;
    Bool LinkStatus = ProgramAttributeImpl.LinkStatus;
    Bool ValidateStatus = ProgramAttributeImpl.ValidateStatus;
    Int InfoLogLength = ProgramAttributeImpl.InfoLogLength;
    Int AttachedShaders = ProgramAttributeImpl.AttachedShaders;
    Int ActiveAtomicCounterBuffers = ProgramAttributeImpl.ActiveAtomicCounterBuffers;
    Int ActiveAttributes = ProgramAttributeImpl.ActiveAttributes;
    Int ActiveAttributeMaxLength = ProgramAttributeImpl.ActiveAttributeMaxLength;
    Int ActiveUniforms = ProgramAttributeImpl.ActiveUniforms;
    Int ActiveUniformBlocks = ProgramAttributeImpl.ActiveUniformBlocks;
    Int ActiveUniformBlockMaxNameLength = ProgramAttributeImpl.ActiveUniformBlockMaxNameLength;
    Int ActiveUniformMaxLength = ProgramAttributeImpl.ActiveUniformMaxLength;
    Int ComputeWorkGroupSize = ProgramAttributeImpl.ComputeWorkGroupSize;
    Int ProgramBinaryLength = ProgramAttributeImpl.ProgramBinaryLength;
    Int TransformFeedbackBufferMode = ProgramAttributeImpl.TransformFeedbackBufferMode;
    Int TransformFeedbackVaryings = ProgramAttributeImpl.TransformFeedbackVaryings;
    Int TransformFeedbackVaryingMaxLength = ProgramAttributeImpl.TransformFeedbackVaryingMaxLength;
    Int GeometryVerticesOut = ProgramAttributeImpl.GeometryVerticesOut;
    Int GeometryInputType = ProgramAttributeImpl.GeometryInputType;
    Int GeometryOutputType = ProgramAttributeImpl.GeometryOutputType;

    static ProgramAttribute[] values() {
        return ProgramAttributeImpl.values();
    }

    sealed interface Bool extends ProgramAttribute, IntEnum permits ProgramAttributeImpl {

    }

    sealed interface Int extends ProgramAttribute, IntEnum permits ProgramAttributeImpl {

    }
}
