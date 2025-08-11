package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Query extends Handle, Bindable<Query.Type, Query> {
    enum Type implements BindTarget<Type, Query> {
        SamplesPassed("SAMPLES_PASSED"),
        AnySamplesPassed("ANY_SAMPLES_PASSED"),
        AnySamplesPassedConservative("ANY_SAMPLES_PASSED_CONSERVATIVE"),
        PrimitivesGenerated("PRIMITIVES_GENERATED"),
        TransformFeedbackPrimitivesWritten("TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN"),
        TimeElapsed("TIME_ELAPSED");
        private final int value;

        Type(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
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
