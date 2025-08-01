package com.vanix.easygl.opengl;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.meta.*;
import com.vanix.easygl.core.graphics.*;

import java.lang.annotation.ElementType;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GlMetaService extends AbstractMetaService {

    static final BindableMeta<Buffer.Type, Buffer> BufferMeta = new IntBindableMeta<>(
            GlBuffer::new,
            GLX::glBindBuffer,
            GLX::glBindBuffer,
            0,
            GLX::glDeleteBuffers,
            GlBuffer::new,
            GLX::glGenBuffers,
            GLX::glDeleteBuffers);

    static final BindableMeta<BindTarget.Default<VertexArray>, VertexArray> VertexArrayMeta = new IntBindableMeta<>(
            GlVertexArray::new,
            (target, handle) -> GLX.glBindVertexArray(handle),
            (target, handle) -> GLX.glBindVertexArray(handle),
            0,
            GLX::glDeleteVertexArrays,
            GlVertexArray::new,
            GLX::glGenVertexArrays,
            GLX::glDeleteVertexArrays);

    static final BindableMeta<FrameBuffer.Type, FrameBuffer> FrameBufferMeta = new IntBindableMeta<>(
            GlFrameBuffer::new,
            GLX::glBindFramebuffer,
            GLX::glBindFramebuffer,
            0,
            GLX::glDeleteFramebuffers,
            GlFrameBuffer::new,
            GLX::glGenFramebuffers,
            GLX::glDeleteFramebuffers);

    static final HandleMeta<Shader> ShaderMeta = new IntHandleMeta<>(
            GlShader::new,
            GLX::glDeleteShader,
            GlShader::new,
            null,
            null);

    static final BindableMeta<BindTarget.Default<Program>, Program> ProgramMeta = createProgramMeta(
            GlProgram::new,
            GlProgram::new);

    static final BindableMeta<BindTarget.Default<UniformProgram<ElementType>>, UniformProgram<ElementType>> UniformProgramMeta = createProgramMeta(
            GlUniformProgram::new,
            GlUniformProgram::new);

    static final BindableMeta<BindTarget.Default<RenderBuffer>, RenderBuffer> RenderBufferMeta = new IntBindableMeta<>(
            GlRenderBuffer::new,
            GLX::glBindRenderbuffer,
            GLX::glBindRenderbuffer,
            0,
            GLX::glDeleteRenderbuffers,
            GlRenderBuffer::new,
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
            GlTexture2D::new,
            GlTexture2D::new);

    static final BindableMeta<Texture.Type<TextureCube>, TextureCube> TextureCubeMeta = createTextureMeta(
            GlTextureCube::new,
            GlTextureCube::new);

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
        register(UniformProgram.class, UniformProgramMeta);
    }

    @Override
    public String system() {
        return "Graphics";
    }

    @Override
    public int queryInt(String id) {
        try {
            return GLX.class.getField("GL_" + id).getInt(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException("No constance of id: " + id);
        }
    }

    @Override
    public int getError() {
        return GLX.glGetError();
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

    private static <T extends IProgram<T>>
    BindableMeta<BindTarget.Default<T>, T> createProgramMeta(Function<Object[], T> factory,
                                                             BiFunction<Integer, Object[], T> init) {
        return new IntBindableMeta<>(
                factory,
                (target, handle) -> GLX.glUseProgram(handle),
                (target, handle) -> GLX.glUseProgram(handle),
                0,
                GLX::glDeleteProgram,
                init);
    }

}
