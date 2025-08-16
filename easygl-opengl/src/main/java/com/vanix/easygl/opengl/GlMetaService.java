package com.vanix.easygl.opengl;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.*;
import com.vanix.easygl.core.meta.*;
import org.lwjgl.opengl.GL45;

import java.util.function.BiFunction;
import java.util.function.Function;

@SystemName("Graphics")
public class GlMetaService extends AbstractMetaService {

    static final BindableMeta<Buffer.Type, Buffer> BufferMeta = new IntBindableMeta<>(
            args -> new GlBuffer((DataType) args[0]),
            GLX::glBindBuffer,
            GLX::glBindBuffer,
            0,
            GLX::glDeleteBuffers,
            (handle, args) -> new GlBuffer(handle, (DataType) args[0]),
            GLX::glGenBuffers,
            GLX::glDeleteBuffers);

    static final BindableMeta<BindTarget.Default<VertexArray>, VertexArray> VertexArrayMeta = new IntBindableMeta<>(
            args -> new GlVertexArray(),
            (target, handle) -> GLX.glBindVertexArray(handle),
            (target, handle) -> GLX.glBindVertexArray(handle),
            0,
            GLX::glDeleteVertexArrays,
            (handle, args) -> new GlVertexArray(handle),
            GLX::glGenVertexArrays,
            GLX::glDeleteVertexArrays);

    static final BindableMeta<FrameBuffer.Target, FrameBuffer> FrameBufferMeta = new IntBindableMeta<>(
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

    static final BindableMeta<Texture.Type<Texture2D>, Texture2D> Texture2DMeta = createTextureMeta(
            args -> new GlTexture2D(),
            (handle, args) -> new GlTexture2D(handle));

    static final BindableMeta<Texture.Type<TextureCube>, TextureCube> TextureCubeMeta = createTextureMeta(
            args -> new GlTextureCube(),
            (handle, args) -> new GlTextureCube(handle));

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

    public GlMetaService() {
        register(Buffer.class, BufferMeta);
        register(FrameBuffer.class, FrameBufferMeta);
        register(VertexArray.class, VertexArrayMeta);
        register(RenderBuffer.class, RenderBufferMeta);
        register(Program.class, ProgramMeta);
        register(Texture.Unit.class, TextureUnitMeta);
        register(Texture2D.class, Texture2DMeta);
        register(TextureCube.class, TextureCubeMeta);
        register(Shader.class, ShaderMeta);
        register(Sync.class, SyncMeta);
        register(Query.SampleQuery.class, SampleQueryMeta);
        register(Query.IndexQuery.class, IndexQueryMeta);
        register(Query.TimerQuery.class, TimerQueryMeta);
    }

    @Override
    public int queryInt(String id) {
        if (id.startsWith("GET.")) {
            id = id.substring(4);
            return GLX.glGetInteger(queryStaticIntField(GL45.class, "GL_", id).orElseThrow());
        }
        return queryStaticIntField(GL45.class, "GL_", id).orElseThrow();
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
    BindableMeta<Texture.Type<T>, T> createTextureMeta(Function<Object[], T> factory,
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
