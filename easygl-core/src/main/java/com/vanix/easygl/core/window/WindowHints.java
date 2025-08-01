package com.vanix.easygl.core.window;

import com.vanix.easygl.core.meta.MetaSystem;

import static com.vanix.easygl.core.window.WindowHint.*;

public interface WindowHints {

    WindowHint.BooleanHint
            Resizable = ofBoolean(MetaSystem.Window.queryInt("RESIZABLE")),
            Visible = ofBoolean(MetaSystem.Window.queryInt("VISIBLE")),
            Decorated = ofBoolean(MetaSystem.Window.queryInt("DECORATED")),
            Focused = ofBoolean(MetaSystem.Window.queryInt("FOCUSED")),
            AutoIconify = ofBoolean(MetaSystem.Window.queryInt("AUTO_ICONIFY")),
            Floating = ofBoolean(MetaSystem.Window.queryInt("FLOATING")),
            Maximized = ofBoolean(MetaSystem.Window.queryInt("MAXIMIZED")),
            CenterCursor = ofBoolean(MetaSystem.Window.queryInt("CENTER_CURSOR")),
            TransparentFrameBuffer = ofBoolean(MetaSystem.Window.queryInt("TRANSPARENT_FRAMEBUFFER")),
            FocusOnShow = ofBoolean(MetaSystem.Window.queryInt("FOCUS_ON_SHOW")),
            ScaleToMonitor = ofBoolean(MetaSystem.Window.queryInt("SCALE_TO_MONITOR")),
            MousePassThrough = ofBoolean(MetaSystem.Window.queryInt("MOUSE_PASSTHROUGH")),
            Stereo = ofBoolean(MetaSystem.Window.queryInt("STEREO")),
            SRGBCapable = ofBoolean(MetaSystem.Window.queryInt("SRGB_CAPABLE")),
            DoubleBuffer = ofBoolean(MetaSystem.Window.queryInt("DOUBLEBUFFER")),
            ContextNoError = ofBoolean(MetaSystem.Window.queryInt("CONTEXT_NO_ERROR")),
            OpenglForwardCompat = ofBoolean(MetaSystem.Window.queryInt("OPENGL_FORWARD_COMPAT")),
            OpenglDebugContext = ofBoolean(MetaSystem.Window.queryInt("OPENGL_DEBUG_CONTEXT")),
            Win32KeyboardMenu = ofBoolean(MetaSystem.Window.queryInt("WIN32_KEYBOARD_MENU")),
            CocoaRetinaFrameBuffer = ofBoolean(MetaSystem.Window.queryInt("COCOA_RETINA_FRAMEBUFFER")),
            CocoaGraphicsSwitching = ofBoolean(MetaSystem.Window.queryInt("COCOA_GRAPHICS_SWITCHING"));

    WindowHint.IntHint
            RedBits = ofInt(MetaSystem.Window.queryInt("RED_BITS")),
            GreenBits = ofInt(MetaSystem.Window.queryInt("GREEN_BITS")),
            BlueBits = ofInt(MetaSystem.Window.queryInt("BLUE_BITS")),
            AlphaBits = ofInt(MetaSystem.Window.queryInt("ALPHA_BITS")),
            DepthBits = ofInt(MetaSystem.Window.queryInt("DEPTH_BITS")),
            StencilBits = ofInt(MetaSystem.Window.queryInt("STENCIL_BITS")),
            AccumRedBits = ofInt(MetaSystem.Window.queryInt("ACCUM_RED_BITS")),
            AccumGreenBits = ofInt(MetaSystem.Window.queryInt("ACCUM_GREEN_BITS")),
            AccumBlueBits = ofInt(MetaSystem.Window.queryInt("ACCUM_BLUE_BITS")),
            AccumAlphaBits = ofInt(MetaSystem.Window.queryInt("ACCUM_ALPHA_BITS")),
            AuxBuffers = ofInt(MetaSystem.Window.queryInt("AUX_BUFFERS")),
            Samples = ofInt(MetaSystem.Window.queryInt("SAMPLES")),
            RefreshRate = ofInt(MetaSystem.Window.queryInt("REFRESH_RATE")),
            ContextCreationApi = ofInt(MetaSystem.Window.queryInt("CONTEXT_CREATION_API")),
            ContextVersionMajor = ofInt(MetaSystem.Window.queryInt("CONTEXT_VERSION_MAJOR")),
            ContextVersionMinor = ofInt(MetaSystem.Window.queryInt("CONTEXT_VERSION_MINOR")),
            ContextRobustness = ofInt(MetaSystem.Window.queryInt("CONTEXT_ROBUSTNESS")),
            ContextReleaseBehavior = ofInt(MetaSystem.Window.queryInt("CONTEXT_RELEASE_BEHAVIOR"));

    WindowHint.StringHint
            CocoaFrameName = ofString(MetaSystem.Window.queryInt("COCOA_FRAME_NAME")),
            X11ClassName = ofString(MetaSystem.Window.queryInt("X11_CLASS_NAME")),
            X11InstanceName = ofString(MetaSystem.Window.queryInt("X11_INSTANCE_NAME")),
            WaylandAppId = ofString(MetaSystem.Window.queryInt("WAYLAND_APP_ID"));

    enum OpenGlProfile implements WindowHint {
        Any(MetaSystem.Window.queryInt("OPENGL_ANY_PROFILE")),
        Core(MetaSystem.Window.queryInt("OPENGL_CORE_PROFILE")),
        Compat(MetaSystem.Window.queryInt("OPENGL_COMPAT_PROFILE"));
        private static final IntHint HINT = ofInt(MetaSystem.Window.queryInt("OPENGL_PROFILE"));
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
        No(MetaSystem.Window.queryInt("NO_API")),
        OpenGl(MetaSystem.Window.queryInt("OPENGL_API")),
        OpenGLEs(MetaSystem.Window.queryInt("OPENGL_ES_API"));
        private static final IntHint HINT = ofInt(MetaSystem.Window.queryInt("CLIENT_API"));
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
