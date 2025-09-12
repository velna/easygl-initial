package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.Pipeline;
import com.vanix.easygl.core.graphics.Program;

import java.util.function.IntConsumer;

public class GlPipeline extends AbstractBindable<BindTarget.Default<Pipeline>, Pipeline> implements Pipeline {
    public GlPipeline() {
        this(GLX.glGenProgramPipelines());
    }

    public GlPipeline(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteProgramPipelines);
    }

    @Override
    public Pipeline useProgramStages(Program program, Stage stage) {
        assertBinding();
        GLX.glUseProgramStages(intHandle(), stage.value(), program.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Pipeline useProgramStages(Program program, BitSet<Stage> stages) {
        assertBinding();
        GLX.glUseProgramStages(intHandle(), stages.value(), program.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Pipeline activeShaderProgram(Program program) {
        assertBinding();
        GLX.glActiveShaderProgram(intHandle(), program.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Pipeline validate() {
        GLX.glValidateProgramPipeline(intHandle());
        GLX.checkError();
        return this;
    }
}
