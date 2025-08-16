package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.CloseableArray;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.meta.HandleMeta;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Query<T extends Query<T>> extends Handle {
    HandleMeta<SampleQuery> SampleQueryMeta = MetaSystem.Graphics.of(SampleQuery.class);
    HandleMeta<IndexQuery> IndexQueryMeta = MetaSystem.Graphics.of(IndexQuery.class);
    HandleMeta<TimerQuery> TimerQueryMeta = MetaSystem.Graphics.of(TimerQuery.class);

    T begin();

    T end();

    int getIntResult();

    int getIntResultNoWait();

    long getLongResult();

    long getLongResultNoWait();

    boolean isResultAvailable();

    static SampleQuery ofSample(SampleType type) {
        return SampleQueryMeta.create(type);
    }

    static CloseableArray<SampleQuery> ofSample(SampleType type, int n) {
        return SampleQueryMeta.createArray(n, type);
    }

    static IndexQuery ofIndex(IndexType type) {
        return IndexQueryMeta.create(type);
    }

    static CloseableArray<IndexQuery> ofIndex(IndexType type, int n) {
        return IndexQueryMeta.createArray(n, type);
    }

    static TimerQuery ofTimer() {
        return TimerQueryMeta.create();
    }

    static CloseableArray<TimerQuery> ofTimer(int n) {
        return TimerQueryMeta.createArray(n);
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
