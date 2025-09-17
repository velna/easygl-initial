package com.vanix.easygl.learnopengl;

import com.vanix.easygl.core.graphics.program.UniformChain;

public class Uniforms<T extends Uniforms<T>> {
    public UniformChain<T> model;
    public UniformChain<T> view;
    public UniformChain<T> projection;
    public UniformChain<T> viewPos;
}
