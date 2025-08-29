package com.vanix.easygl.opengl;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.meta.*;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@SystemName("Graphics")
public class GlMetaService extends AbstractMetaService {
    static final BindableMeta<Buffer.Target, Buffer> BufferMeta = new IntBindableMeta<>(
            args -> new GlBuffer((DataType) args[0]),
            GLX::glBindBuffer,
            GLX::glBindBuffer,
            0,
            GLX::glDeleteBuffers,
            (handle, args) -> new GlBuffer(handle, (DataType) args[0]),
            GLX::glGenBuffers,
            GLX::glDeleteBuffers,
            GlBufferArray::new);

    static final BindableMeta<BindTarget.Default<VertexArray>, VertexArray> VertexArrayMeta = new IntBindableMeta<>(
            args -> new GlVertexArray(),
            (target, handle) -> GLX.glBindVertexArray(handle),
            (target, handle) -> GLX.glBindVertexArray(handle),
            0,
            GLX::glDeleteVertexArrays,
            (handle, args) -> new GlVertexArray(handle),
            GLX::glGenVertexArrays,
            GLX::glDeleteVertexArrays);

    static final BindableMeta<BaseFrameBuffer.Target<FrameBuffer>, FrameBuffer> FrameBufferMeta = new IntBindableMeta<>(
            args -> new GlFrameBuffer(),
            GLX::glBindFramebuffer,
            GLX::glBindFramebuffer,
            0,
            GLX::glDeleteFramebuffers,
            (handle, args) -> new GlFrameBuffer(handle),
            GLX::glGenFramebuffers,
            GLX::glDeleteFramebuffers);

    static final HandleMeta<Shader> ShaderMeta = new IntHandleMeta<>(
            args -> new GlShader((Shader.Type) args[0]),
            GLX::glDeleteShader,
            (handle, args) -> new GlShader(handle, (Shader.Type) args[0]),
            null,
            null);

    static final BindableMeta<BindTarget.Default<Program>, Program> ProgramMeta = new IntBindableMeta<>(
            args -> new GlProgram(),
            (target, handle) -> GLX.glUseProgram(handle),
            (target, handle) -> GLX.glUseProgram(handle),
            0,
            GLX::glDeleteProgram,
            (handle, args) -> new GlProgram(handle));

    static final BindableMeta<BindTarget.Default<Pipeline>, Pipeline> PipelineMeta = new IntBindableMeta<>(
            args -> new GlPipeline(),
            (target, handle) -> GLX.glBindProgramPipeline(handle),
            (target, handle) -> GLX.glBindProgramPipeline(handle),
            0,
            GLX::glDeleteProgramPipelines,
            (handle, args) -> new GlPipeline(handle),
            GLX::glGenProgramPipelines,
            GLX::glDeleteProgramPipelines);

    static final BindableMeta<BindTarget.Default<RenderBuffer>, RenderBuffer> RenderBufferMeta = new IntBindableMeta<>(
            args -> new GlRenderBuffer(),
            GLX::glBindRenderbuffer,
            GLX::glBindRenderbuffer,
            0,
            GLX::glDeleteRenderbuffers,
            (handle, args) -> new GlRenderBuffer(handle),
            GLX::glGenRenderbuffers,
            GLX::glDeleteRenderbuffers);

    static final BindableMeta<Texture.Unit, Texture.Unit> TextureUnitMeta = new IntBindableMeta<>(
            null,
            (target, h) -> GLX.glActiveTexture(h),
            (target, h) -> GLX.glActiveTexture(h),
            GLX.GL_TEXTURE0,
            null,
            null
    );

    static final BindableMeta<Texture.Target<Texture2D>, Texture2D> Texture2DMeta = createTextureMeta(
            args -> new GlTexture2D(),
            (handle, args) -> new GlTexture2D(handle));

    static final BindableMeta<Texture.Target<TextureCube>, TextureCube> TextureCubeMeta = createTextureMeta(
            args -> new GlTextureCube(),
            (handle, args) -> new GlTextureCube(handle));

    static final BindableMeta<Texture.Target<TextureMultiSample>, TextureMultiSample> TextureMultiSampleMeta = createTextureMeta(
            args -> new GlTextureMultiSample(),
            (handle, args) -> new GlTextureMultiSample(handle));

    static final Meta<Sync> SyncMeta = new DefaultMeta<>(args -> new GlSync());

    static final HandleMeta<Query.SampleQuery> SampleQueryMeta = new IntHandleMeta<>(
            args -> new GlQuery.GlSampleQuery((Query.SampleType) args[0]),
            GLX::glDeleteQueries,
            (handle, args) -> new GlQuery.GlSampleQuery(handle, (Query.SampleType) args[0]),
            GLX::glGenQueries,
            GLX::glDeleteQueries);

    static final HandleMeta<Query.IndexQuery> IndexQueryMeta = new IntHandleMeta<>(
            args -> new GlQuery.GlIndexQuery((Query.IndexType) args[0]),
            GLX::glDeleteQueries,
            (handle, args) -> new GlQuery.GlIndexQuery(handle, (Query.IndexType) args[0]),
            GLX::glGenQueries,
            GLX::glDeleteQueries);

    static final HandleMeta<Query.TimerQuery> TimerQueryMeta = new IntHandleMeta<>(
            args -> new GlQuery.GlTimerQuery(),
            GLX::glDeleteQueries,
            (handle, args) -> new GlQuery.GlTimerQuery(handle),
            GLX::glGenQueries,
            GLX::glDeleteQueries);

    @SuppressWarnings("unchecked")
    public GlMetaService() {
        register(Buffer.class, BufferMeta);
        register(BaseFrameBuffer.class, FrameBufferMeta);
        register(FrameBuffer.class, FrameBufferMeta);
        register(VertexArray.class, VertexArrayMeta);
        register(RenderBuffer.class, RenderBufferMeta);
        register(Program.class, ProgramMeta);
        register(Texture.Unit.class, TextureUnitMeta);
        register(Texture2D.class, Texture2DMeta);
        register(TextureCube.class, TextureCubeMeta);
        register(TextureMultiSample.class, TextureMultiSampleMeta);
        register(Shader.class, ShaderMeta);
        register(Sync.class, SyncMeta);
        register(Query.SampleQuery.class, SampleQueryMeta);
        register(Query.IndexQuery.class, IndexQueryMeta);
        register(Query.TimerQuery.class, TimerQueryMeta);
        register(Pipeline.class, PipelineMeta);
        register(ShaderArray.class, (Function<Object[], ShaderArray>) args -> new GlShaderArray((List<Shader>) args[0]));
    }

    @Override
    public int queryInt(String id) {
        if (id.startsWith("GET.")) {
            String mid = id.substring(4);
            return GLX.glGetInteger(queryStaticIntField(GLX.class, "GL_", mid).orElseThrow(() -> new IllegalArgumentException(mid)));
        } else {
            return queryStaticIntField(GLX.class, "GL_", id).orElseThrow(() -> new IllegalArgumentException(id));
        }
    }

    @Override
    public ErrorCode getError() {
        int code = GLX.glGetError();
        if (code == GLX.GL_NO_ERROR) {
            return GraphicsErrorCode.NoError;
        }
        code = code & 0xff;
        return switch (code) {
            case GLX.GL_INVALID_ENUM & 0xf -> GraphicsErrorCode.InvalidEnum;
            case GLX.GL_INVALID_OPERATION & 0xf -> GraphicsErrorCode.InvalidOperation;
            case GLX.GL_INVALID_VALUE & 0xf -> GraphicsErrorCode.InvalidValue;
            case GLX.GL_STACK_OVERFLOW & 0xf -> GraphicsErrorCode.StackOverflow;
            case GLX.GL_STACK_UNDERFLOW & 0xf -> GraphicsErrorCode.StackUnderflow;
            case GLX.GL_INVALID_FRAMEBUFFER_OPERATION & 0xf -> GraphicsErrorCode.InvalidFramebufferOperation;
            case GLX.GL_OUT_OF_MEMORY & 0xf -> GraphicsErrorCode.OutOfMemory;
            default -> GraphicsErrorCode.Unknown;
        };
    }

    @Override
    public void close() {
        // no ops
    }

    private static <T extends Texture<T>>
    BindableMeta<Texture.Target<T>, T> createTextureMeta(Function<Object[], T> factory,
                                                         BiFunction<Integer, Object[], T> init) {
        return new IntBindableMeta<>(
                factory,
                GLX::glBindTexture,
                GLX::glBindTexture,
                0,
                GLX::glDeleteTextures,
                init,
                GLX::glGenTextures,
                GLX::glDeleteTextures
        );
    }

}
