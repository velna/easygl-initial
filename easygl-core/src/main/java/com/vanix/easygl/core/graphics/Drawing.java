package com.vanix.easygl.core.graphics;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface Drawing {

    Drawable build();

    VertexArray vertexArray();

    DrawMode mode();

    @Nullable
    DataType dataType();

    int count();

    @Nullable
    Object indices();

    interface Arrays extends Drawing {
        int first();

        MultiFirsts multiFirsts(int[] counts, int drawCount, int[] firsts);

        MultiFirsts multiFirsts(IntBuffer counts, int drawCount, IntBuffer firsts);

        Instanced instanced(int instanceCount);

        Indirect indirect(int[] indirect);

        Indirect indirect(IntBuffer indirect);

        Indirect indirect(ByteBuffer indirect);
    }

    interface Elements extends Drawing {
        Object indices();

        BaseVertex baseVertex(int baseVertex);

        Instanced instanced(int instanceCount);

        Indirect indirect();

        MultiCounts multiCounts(int[] counts, int drawCount);

        MultiCounts multiCounts(IntBuffer counts, int drawCount);

        MultiBaseVertex multiBaseVertex(int[] counts, int drawCount, int[] baseVertexes);

        MultiBaseVertex multiBaseVertex(IntBuffer counts, int drawCount, IntBuffer baseVertexes);

        Range range(int min, int max);
    }

    interface Instanced extends Drawing {
        int instanceCount();

        BaseInstanced baseInstanced(int baseInstance);
    }

    interface BaseInstanced extends Instanced {
        int baseInstance();
    }

    interface Indirect extends Drawing {
        Object indirect();

        MultiStride multiStride(int stride, int drawCount);
    }

    interface MultiCounts extends Drawing {
        Object counts();

        int drawCount();
    }

    interface MultiFirsts extends MultiCounts {
        Object firsts();
    }

    interface MultiStride extends Indirect {
        int stride();

        int drawCount();
    }

    interface Range extends Drawing {
        int min();

        int max();
    }

    interface BaseVertex {
        int baseVertex();

        Instanced instanced(int instanceCount);

        Range range(int min, int max);
    }

    interface MultiBaseVertex extends MultiCounts {
        Object baseVertexes();
    }

}
