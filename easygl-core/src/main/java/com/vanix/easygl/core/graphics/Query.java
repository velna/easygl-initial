package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;

public interface Query extends Handle, Bindable<Query> {
    enum Type {
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

        public int value() {
            return value;
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
