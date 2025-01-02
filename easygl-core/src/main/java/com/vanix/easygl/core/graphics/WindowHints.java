package com.vanix.easygl.core.graphics;

import org.lwjgl.glfw.GLFW;

import static com.vanix.easygl.core.graphics.WindowHint.*;

public interface WindowHints {

    WindowHint.BooleanHint
            Resizable = ofBoolean(GLFW.GLFW_RESIZABLE),
            Visible = ofBoolean(GLFW.GLFW_VISIBLE),
            Decorated = ofBoolean(GLFW.GLFW_DECORATED),
            Focused = ofBoolean(GLFW.GLFW_FOCUSED),
            AutoIconify = ofBoolean(GLFW.GLFW_AUTO_ICONIFY),
            Floating = ofBoolean(GLFW.GLFW_FLOATING),
            Maximized = ofBoolean(GLFW.GLFW_MAXIMIZED),
            CenterCursor = ofBoolean(GLFW.GLFW_CENTER_CURSOR),
            TransparentFrameBuffer = ofBoolean(GLFW.GLFW_TRANSPARENT_FRAMEBUFFER),
            FocusOnShow = ofBoolean(GLFW.GLFW_FOCUS_ON_SHOW),
            ScaleToMonitor = ofBoolean(GLFW.GLFW_SCALE_TO_MONITOR),
            MousePassThrough = ofBoolean(GLFW.GLFW_MOUSE_PASSTHROUGH),
            Stereo = ofBoolean(GLFW.GLFW_STEREO),
            SRGBCapable = ofBoolean(GLFW.GLFW_SRGB_CAPABLE),
            DoubleBuffer = ofBoolean(GLFW.GLFW_DOUBLEBUFFER),
            ContextNoError = ofBoolean(GLFW.GLFW_CONTEXT_NO_ERROR),
            OpenglForwardCompat = ofBoolean(GLFW.GLFW_OPENGL_FORWARD_COMPAT),
            OpenglDebugContext = ofBoolean(GLFW.GLFW_OPENGL_DEBUG_CONTEXT),
            Win32KeyboardMenu = ofBoolean(GLFW.GLFW_WIN32_KEYBOARD_MENU),
            CocoaRetinaFrameBuffer = ofBoolean(GLFW.GLFW_COCOA_RETINA_FRAMEBUFFER),
            CocoaGraphicsSwitching = ofBoolean(GLFW.GLFW_COCOA_GRAPHICS_SWITCHING);

    WindowHint.IntHint
            RedBits = ofInt(GLFW.GLFW_RED_BITS),
            GreenBits = ofInt(GLFW.GLFW_GREEN_BITS),
            BlueBits = ofInt(GLFW.GLFW_BLUE_BITS),
            AlphaBits = ofInt(GLFW.GLFW_ALPHA_BITS),
            DepthBits = ofInt(GLFW.GLFW_DEPTH_BITS),
            StencilBits = ofInt(GLFW.GLFW_STENCIL_BITS),
            AccumRedBits = ofInt(GLFW.GLFW_ACCUM_RED_BITS),
            AccumGreenBits = ofInt(GLFW.GLFW_ACCUM_GREEN_BITS),
            AccumBlueBits = ofInt(GLFW.GLFW_ACCUM_BLUE_BITS),
            AccumAlphaBits = ofInt(GLFW.GLFW_ACCUM_ALPHA_BITS),
            AuxBuffers = ofInt(GLFW.GLFW_AUX_BUFFERS),
            Samples = ofInt(GLFW.GLFW_SAMPLES),
            RefreshRate = ofInt(GLFW.GLFW_REFRESH_RATE),
            ContextCreationApi = ofInt(GLFW.GLFW_CONTEXT_CREATION_API),
            ContextVersionMajor = ofInt(GLFW.GLFW_CONTEXT_VERSION_MAJOR),
            ContextVersionMinor = ofInt(GLFW.GLFW_CONTEXT_VERSION_MINOR),
            ContextRobustness = ofInt(GLFW.GLFW_CONTEXT_ROBUSTNESS),
            ContextReleaseBehavior = ofInt(GLFW.GLFW_CONTEXT_RELEASE_BEHAVIOR);

    WindowHint.StringHint
            CocoaFrameName = ofString(GLFW.GLFW_COCOA_FRAME_NAME),
            X11ClassName = ofString(GLFW.GLFW_X11_CLASS_NAME),
            X11InstanceName = ofString(GLFW.GLFW_X11_INSTANCE_NAME),
            WaylandAppId = ofString(GLFW.GLFW_WAYLAND_APP_ID);

    enum OpenGlProfile implements WindowHint {
        Any(GLFW.GLFW_OPENGL_ANY_PROFILE),
        Core(GLFW.GLFW_OPENGL_CORE_PROFILE),
        Compat(GLFW.GLFW_OPENGL_COMPAT_PROFILE);
        private static final IntHint HINT = ofInt(GLFW.GLFW_OPENGL_PROFILE);
        private final int value;

        OpenGlProfile(int value) {
            this.value = value;
        }

        @Override
        public int key() {
            return HINT.key();
        }

        public void set() {
            HINT.set(value);
        }
    }

    enum ClientApi implements WindowHint {
        No(GLFW.GLFW_NO_API),
        OpenGl(GLFW.GLFW_OPENGL_API),
        OpenGLEs(GLFW.GLFW_OPENGL_ES_API);
        private static final IntHint HINT = ofInt(GLFW.GLFW_CLIENT_API);
        private final int value;

        ClientApi(int value) {
            this.value = value;
        }

        @Override
        public int key() {
            return HINT.key();
        }

        public void set() {
            HINT.set(value);
        }
    }
}
