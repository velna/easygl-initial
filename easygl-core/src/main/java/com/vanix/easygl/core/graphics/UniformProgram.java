package com.vanix.easygl.core.graphics;


import java.nio.FloatBuffer;

public interface UniformProgram<E extends Enum<E>> extends IProgram<UniformProgram<E>> {

    @Override
    UniformProgram<E> bind();

    UniformProgram<E> set(E key, boolean value) throws GraphicsException;

    UniformProgram<E> set(E key, int value) throws GraphicsException;

    UniformProgram<E> set(E key, float value) throws GraphicsException;

    UniformProgram<E> set(E key, float[] value) throws GraphicsException;

    UniformProgram<E> set(E key, FloatBuffer buffer) throws GraphicsException;

    UniformProgram<E> set(E key, Texture.Unit unit, Texture<?> texture) throws GraphicsException;
}
