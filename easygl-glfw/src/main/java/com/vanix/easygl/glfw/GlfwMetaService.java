package com.vanix.easygl.glfw;

import com.vanix.easygl.core.meta.AbstractMetaService;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.LongBindableMeta;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.window.Window;
import com.vanix.easygl.core.window.WindowHint;
import org.lwjgl.glfw.GLFW;

import java.util.function.Function;

public class GlfwMetaService extends AbstractMetaService {
    static final BindableMeta<BindTarget.Default<Window>, Window> WindowMeta = new LongBindableMeta<>(
            GlWindow::new,
            (target, h) -> GLFW.glfwMakeContextCurrent(h),
            (target, h) -> GLFW.glfwMakeContextCurrent(h),
            0L
    );

    public GlfwMetaService() {
        GLFW.glfwInit();
        register(Window.class, WindowMeta);
        register(WindowHint.IntHint.class,
                (Function<Object[], ?>) args -> new GlWindowHint.GlIntHint((Integer) args[0]));
        register(WindowHint.BooleanHint.class,
                (Function<Object[], ?>) args -> new GlWindowHint.GlBooleanHint((Integer) args[0]));
        register(WindowHint.StringHint.class,
                (Function<Object[], ?>) args -> new GlWindowHint.GlStringHint((Integer) args[0]));
    }

    @Override
    public String system() {
        return "Window";
    }

    @Override
    public int queryInt(String id) {
        try {
            return GLFW.class.getField("GLFW_" + id).getInt(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException("No constance of id: " + id);
        }
    }

    @Override
    public int getError() {
        return GLFW.glfwGetError(null);
    }

    @Override
    public void close() {
        GLFW.glfwTerminate();
    }
}
