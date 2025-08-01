package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Query extends Handle, Bindable<Query.Type, Query> {
    enum Type implements BindTarget<Type, Query> {
        SamplesPassed(MetaSystem.Graphics.queryInt("SAMPLES_PASSED")),
        AnySamplesPassed(MetaSystem.Graphics.queryInt("ANY_SAMPLES_PASSED")),
        AnySamplesPassedConservative(MetaSystem.Graphics.queryInt("ANY_SAMPLES_PASSED_CONSERVATIVE")),
        PrimitivesGenerated(MetaSystem.Graphics.queryInt("PRIMITIVES_GENERATED")),
        TransformFeedbackPrimitivesWritten(MetaSystem.Graphics.queryInt("TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN")),
        TimeElapsed(MetaSystem.Graphics.queryInt("TIME_ELAPSED"));
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
