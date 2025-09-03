package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.HandleMeta;
import com.vanix.easygl.core.meta.MetaSystem;

class MetaHolder {
    static final BindableMeta<BindTarget.Default<VertexArray>, VertexArray> VertexArray = MetaSystem.Graphics.of(VertexArray.class);
    static final HandleMeta<Sampler> Sampler = MetaSystem.Graphics.of(Sampler.class);
    static final BindableMeta<Texture.Target<TextureCube>, TextureCube> TextureCube = MetaSystem.Graphics.of(TextureCube.class);
    static final BindableMeta<Texture.Target<TextureMultiSample>, TextureMultiSample> TextureMultiSample = MetaSystem.Graphics.of(TextureMultiSample.class);
    static final BindableMeta<Texture.Target<Texture2D>, Texture2D> Texture2D = MetaSystem.Graphics.of(Texture2D.class);
    static final BindableMeta<BindTarget.Default<TextureUnit>, com.vanix.easygl.core.graphics.TextureUnit> TextureUnit = MetaSystem.Graphics.of(com.vanix.easygl.core.graphics.TextureUnit.class);
    static final com.vanix.easygl.core.meta.Meta<Sync> Sync = MetaSystem.Graphics.of(Sync.class);
    static final HandleMeta<Shader> Shader = MetaSystem.Graphics.of(Shader.class);
    static final BindableMeta<BindTarget.Default<RenderBuffer>, RenderBuffer> RenderBuffer = MetaSystem.Graphics.of(RenderBuffer.class);
    static final HandleMeta<Query.TimerQuery> TimerQuery = MetaSystem.Graphics.of(Query.TimerQuery.class);
    static final HandleMeta<Query.IndexQuery> IndexQuery = MetaSystem.Graphics.of(Query.IndexQuery.class);
    static final HandleMeta<Query.SampleQuery> SampleQuery = MetaSystem.Graphics.of(Query.SampleQuery.class);
    static final BindableMeta<BindTarget.Default<Program>, Program> Program = MetaSystem.Graphics.of(Program.class);
    @SuppressWarnings("rawtypes")
    static final BindableMeta BaseFrameBuffer = MetaSystem.Graphics.of(BaseFrameBuffer.class);
    static final BindableMeta<BaseFrameBuffer.Target<FrameBuffer>, FrameBuffer> FrameBuffer = MetaSystem.Graphics.of(FrameBuffer.class);
    static final BindableMeta<com.vanix.easygl.core.graphics.Buffer.Target, Buffer> Buffer = MetaSystem.Graphics.of(Buffer.class);
    static final BindableMeta<BindTarget.Default<Pipeline>, Pipeline> Pipeline = MetaSystem.Graphics.of(Pipeline.class);
}
