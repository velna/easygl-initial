package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Color;

public interface TextureParameters<T extends TextureParameters<T>> {

    T compareFunc(CompareFunction func);

    CompareFunction compareFunc();

    T compareModeRefToTexture();

    boolean isCompareModeRefToTexture();

    T compareModeNone();

    T loadBias(float value);

    float loadBias();

    T minFilter(MinFilter mf);

    MinFilter minFilter();

    T magFilter(MagFilter mf);

    MagFilter magFilter();

    T minLoad(float value);

    float minLoad();

    T maxLoad(float value);

    float maxLoad();

    T wrapS(Texture.Wrap wrap);

    Texture.Wrap wrapS();

    T wrapT(Texture.Wrap wrap);

    Texture.Wrap wrapT();

    T wrapR(Texture.Wrap wrap);

    Texture.Wrap wrapR();

    T borderColor(float red, float green, float blue, float alpha);

    Color borderColor();

    default T borderColor(Color color) {
        return borderColor(color.red(), color.green(), color.blue(), color.alpha());
    }

}
