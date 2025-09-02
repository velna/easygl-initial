package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Query<T extends Query<T>> extends Handle {

    T begin();

    T end();

    int getIntResult();

    @Support(since = Version.GL44)
    int getIntResultNoWait();

    long getLongResult();

    @Support(since = Version.GL44)
    long getLongResultNoWait();

    boolean isResultAvailable();

    static SampleQuery ofSample(SampleType type) {
        return MetaHolder.SampleQuery.create(type);
    }

    static HandleArray<SampleQuery> ofSample(SampleType type, int n) {
        return MetaHolder.SampleQuery.createArray(n, type);
    }

    static IndexQuery ofIndex(IndexType type) {
        return MetaHolder.IndexQuery.create(type);
    }

    static HandleArray<IndexQuery> ofIndex(IndexType type, int n) {
        return MetaHolder.IndexQuery.createArray(n, type);
    }

    static TimerQuery ofTimer() {
        return MetaHolder.TimerQuery.create();
    }

    static HandleArray<TimerQuery> ofTimer(int n) {
        return MetaHolder.TimerQuery.createArray(n);
    }

    enum SampleType {
        SamplesPassed("SAMPLES_PASSED"),
        AnySamplesPassed("ANY_SAMPLES_PASSED"),
        AnySamplesPassedConservative("ANY_SAMPLES_PASSED_CONSERVATIVE");
        //        TimeElapsed("TIME_ELAPSED");
        private final int value;

        SampleType(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    interface SampleQuery extends Query<SampleQuery> {

    }

    enum IndexType {
        PrimitivesGenerated("PRIMITIVES_GENERATED"),
        TransformFeedbackPrimitivesWritten("TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN");
        private final int value;

        IndexType(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    interface IndexQuery extends Query<IndexQuery> {
        IndexQuery begin(int index);
    }

    interface TimerQuery extends Query<TimerQuery> {
        void count();
    }

}
