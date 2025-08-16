package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.core.graphics.Query;

import java.util.function.IntConsumer;

public interface GlQuery<T extends Query<T>> extends Query<T> {

    int target();

    @SuppressWarnings("unchecked")
    @Override
    default T begin() {
        GLX.glBeginQuery(target(), intHandle());
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    default T end() {
        GLX.glEndQuery(target());
        return (T) this;
    }

    @Override
    default int getIntResult() {
        return GLX.glGetQueryObjecti(intHandle(), GLX.GL_QUERY_RESULT);
    }

    @Override
    default int getIntResultNoWait() {
        return GLX.glGetQueryObjecti(intHandle(), GLX.GL_QUERY_RESULT_NO_WAIT);
    }

    @Override
    default long getLongResult() {
        return GLX.glGetQueryObjecti64(intHandle(), GLX.GL_QUERY_RESULT);
    }

    @Override
    default long getLongResultNoWait() {
        return GLX.glGetQueryObjecti64(intHandle(), GLX.GL_QUERY_RESULT_NO_WAIT);
    }

    @Override
    default boolean isResultAvailable() {
        return GLX.glGetQueryObjecti64(intHandle(), GLX.GL_QUERY_RESULT_AVAILABLE) == GLX.GL_TRUE;
    }

    abstract class AbstractGlQuery<T extends Query<T>> extends AbstractHandle implements GlQuery<T> {
        protected final int target;

        public AbstractGlQuery(int target) {
            this(GLX.glGenQueries(), target);
        }

        public AbstractGlQuery(int handle, int target) {
            super(handle, (IntConsumer) GLX::glDeleteQueries);
            this.target = target;
        }

        @Override
        public int target() {
            return target;
        }
    }

    class GlSampleQuery extends AbstractGlQuery<SampleQuery> implements SampleQuery {
        public GlSampleQuery(SampleType type) {
            super(type.value());
        }

        public GlSampleQuery(int handle, SampleType type) {
            super(handle, type.value());
        }
    }

    class GlIndexQuery extends AbstractGlQuery<IndexQuery> implements IndexQuery {
        public GlIndexQuery(IndexType type) {
            super(type.value());
        }

        public GlIndexQuery(int handle, IndexType type) {
            super(handle, type.value());
        }

        @Override
        public IndexQuery begin(int index) {
            GLX.glBeginQueryIndexed(target, index, intHandle());
            return this;
        }
    }

    class GlTimerQuery extends AbstractGlQuery<TimerQuery> implements TimerQuery {
        public GlTimerQuery() {
            super(GLX.GL_TIME_ELAPSED);
        }

        public GlTimerQuery(int handle) {
            super(handle, GLX.GL_TIME_ELAPSED);
        }

        @Override
        public void count() {
            GLX.glQueryCounter(intHandle(), target);
        }
    }
}
