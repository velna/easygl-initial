package com.vanix.easygl.core.window;

import java.nio.ShortBuffer;

public record GammaRamp(ShortBuffer red, ShortBuffer green, ShortBuffer blue, int size) {
}
