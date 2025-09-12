package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.TransformFeedback;

import java.util.function.IntConsumer;

public class GlTransformFeedback extends AbstractBindable<BindTarget.Default<TransformFeedback>, TransformFeedback> implements TransformFeedback {

    public GlTransformFeedback(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteTransformFeedbacks);
    }

    @Override
    public TransformFeedback begin(PrimitiveMode primitiveMode) {
        assertBinding();
        GLX.glBeginTransformFeedback(primitiveMode.value());
        GLX.checkError();
        return this;
    }

    @Override
    public TransformFeedback end() {
        assertBinding();
        GLX.glEndTransformFeedback();
        GLX.checkError();
        return this;
    }

    @Override
    public TransformFeedback pause() {
        assertBinding();
        GLX.glPauseTransformFeedback();
        GLX.checkError();
        return this;
    }

    @Override
    public TransformFeedback resume() {
        assertBinding();
        GLX.glResumeTransformFeedback();
        GLX.checkError();
        return this;
    }

    @Override
    public TransformFeedback bindBuffer(int bindingPoint, Buffer buffer) {
        assertBinding();
        GLX.glTransformFeedbackBufferBase(intHandle(), bindingPoint, buffer.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public TransformFeedback bindBufferRange(int bindingPoint, Buffer buffer, int offset, int size) {
        assertBinding();
        GLX.glTransformFeedbackBufferRange(intHandle(), bindingPoint, buffer.intHandle(), offset, size);
        GLX.checkError();
        return this;
    }
}
