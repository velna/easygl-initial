package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;

public interface Query extends Handle, Bindable<Query.Type, Query> {
    enum Type implements BindTarget<Type, Query> {
        SamplesPassed(GLC.GL_SAMPLES_PASSED),
        AnySamplesPassed(GLC.GL_ANY_SAMPLES_PASSED),
        AnySamplesPassedConservative(GLC.GL_ANY_SAMPLES_PASSED_CONSERVATIVE),
        PrimitivesGenerated(GLC.GL_PRIMITIVES_GENERATED),
        TransformFeedbackPrimitivesWritten(GLC.GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN),
        TimeElapsed(GLC.GL_TIME_ELAPSED);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        @Override
        public int value() {
            return value;
        }

        @Override
        public BindingState<Type, Query> state() {
            // TODO:
            return null;
        }
    }

    Query begin();

    Query end();

    default Query execute(Runnable runnable) {
        begin();
        try {
            runnable.run();
        } finally {
            end();
        }
        return this;
    }

    int getIntResult();

    long getLongResult();

    static Query of(Type type) {
        return null;
    }

    static Query of(Type type, int index) {
        return null;
    }
}
