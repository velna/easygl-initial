package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.Bindable;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

@Support(since = Version.GL41)
public interface Pipeline extends Bindable<BindTarget.Default<Pipeline>, Pipeline> {

    BindTarget.Default<Pipeline> Target = new BindTarget.Default<>("Pipeline", MetaHolder.Pipeline);

    enum Stage implements IntEnum {
        VertexShader("VERTEX_SHADER_BIT"),
        TessControlShader("TESS_CONTROL_SHADER_BIT"),
        TessEvaluationShader("TESS_EVALUATION_SHADER_BIT"),
        GeometryShader("GEOMETRY_SHADER_BIT"),
        FragmentShaderBit("FRAGMENT_SHADER_BIT"),
        ComputeShader("COMPUTE_SHADER_BIT"),
        AllShader("ALL_SHADER_BITS");
        private final int value;

        Stage(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    @Support(since = Version.GL41)
    Pipeline useProgramStages(Program program, Stage stage);

    @Support(since = Version.GL41)
    Pipeline useProgramStages(Program program, BitSet<Stage> stages);

    @Support(since = Version.GL41)
    Pipeline activeShaderProgram(Program program);

    static Pipeline of() {
        return MetaHolder.Pipeline.create();
    }

    static HandleArray<Pipeline> of(int n) {
        return MetaHolder.Pipeline.createArray(n);
    }
}
