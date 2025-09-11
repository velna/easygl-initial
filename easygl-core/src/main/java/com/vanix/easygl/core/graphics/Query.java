package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.HandleArray;
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

    enum Mode implements IntEnum {
        QueryWait("QUERY_WAIT"),
        QueryNoWait("QUERY_NO_WAIT"),
        QueryByRegionWait("QUERY_BY_REGION_WAIT"),
        QueryByRegionNoWait("QUERY_BY_REGION_NO_WAIT"),
        @Support(since = Version.GL45)
        QueryWaitInverted("QUERY_WAIT_INVERTED"),
        @Support(since = Version.GL45)
        QueryNoWaitInverted("QUERY_NO_WAIT_INVERTED"),
        @Support(since = Version.GL45)
        QueryByRegionWaitInverted("QUERY_BY_REGION_WAIT_INVERTED"),
        @Support(since = Version.GL45)
        QueryByRegionNoWaitInverted("QUERY_BY_REGION_NO_WAIT_INVERTED");
        private final int value;

        Mode(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum SampleType implements IntEnum {
        SamplesPassed("SAMPLES_PASSED"),
        AnySamplesPassed("ANY_SAMPLES_PASSED"),
        @Support(since = Version.GL43)
        AnySamplesPassedConservative("ANY_SAMPLES_PASSED_CONSERVATIVE");
        //        TimeElapsed("TIME_ELAPSED");
        private final int value;

        SampleType(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    interface SampleQuery extends Query<SampleQuery> {
        SampleQuery beginConditionalRender(Mode mode);

        SampleQuery endConditionalRender();
    }

    enum IndexType implements IntEnum {
        PrimitivesGenerated("PRIMITIVES_GENERATED"),
        TransformFeedbackPrimitivesWritten("TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN");
        private final int value;

        IndexType(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
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
