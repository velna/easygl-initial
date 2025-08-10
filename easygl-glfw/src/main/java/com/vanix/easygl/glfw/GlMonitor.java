package com.vanix.easygl.glfw;

import com.vanix.easygl.commons.Position;
import com.vanix.easygl.commons.Rectangle;
import com.vanix.easygl.commons.event.ListenerSupport;
import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.core.window.Monitor;
import com.vanix.easygl.core.window.event.MonitorEvent;
import com.vanix.easygl.core.window.event.MonitorListener;
import org.eclipse.collections.api.factory.primitive.LongObjectMaps;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class GlMonitor extends AbstractHandle implements Monitor {
    private static final MutableLongObjectMap<Monitor> MONITORS = LongObjectMaps.mutable.of();
    static final ListenerSupport<MonitorListener> MonitorListeners = new ListenerSupport<>(1);
    private final int width;
    private final int height;

    public GlMonitor(long handle) {
        super(handle, h -> {
        });
        int[] w = new int[1];
        int[] h = new int[1];
        glfwGetMonitorPhysicalSize(handle, w, h);
        this.width = w[0];
        this.height = h[0];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public double getX() {
        int[] x = new int[1];
        glfwGetMonitorPos(handle, x, null);
        return x[0];
    }

    @Override
    public double getY() {
        int[] y = new int[1];
        glfwGetMonitorPos(handle, null, y);
        return y[0];
    }

    @Override
    public Position getPosition() {
        int[] x = new int[1];
        int[] y = new int[1];
        glfwGetMonitorPos(handle, x, y);
        return Position.of(x[0], y[0]);
    }

    @Override
    public String name() {
        return glfwGetMonitorName(handle);
    }

    @Override
    public Vector2f contentScale() {
        float[] x = new float[1];
        float[] y = new float[1];
        glfwGetMonitorContentScale(handle, x, y);
        return new Vector2f(x[0], y[0]);
    }

    @Override
    public Rectangle workArea() {
        int[] x = new int[1];
        int[] y = new int[1];
        int[] w = new int[1];
        int[] h = new int[1];
        glfwGetMonitorWorkarea(handle, x, y, w, h);
        return Rectangle.of(x[0], y[0], w[0], h[0]);
    }

    static Monitor of(long handle) {
        return MONITORS.getIfAbsentPutWithKey(handle, GlMonitor::new);
    }

    static Monitor primary() {
        long monitor = glfwGetPrimaryMonitor();
        return monitor == MemoryUtil.NULL ? null : of(monitor);
    }

    static Monitor[] monitors() {
        var pointerBuffer = org.lwjgl.glfw.GLFW.glfwGetMonitors();
        if (pointerBuffer == null) {
            MONITORS.clear();
            return new Monitor[0];
        }
        List<Monitor> monitors = new ArrayList<>();
        while (pointerBuffer.hasRemaining()) {
            monitors.add(MONITORS.getIfAbsentPutWithKey(pointerBuffer.get(), GlMonitor::new));
        }
        MONITORS.clear();
        for (var monitor : monitors) {
            MONITORS.put(monitor.handle(), monitor);
        }
        return monitors.toArray(new Monitor[0]);
    }

    static void fireMonitorOnConnection(long monitorHandle, int evt) {
        boolean connect = evt == GLFW.GLFW_CONNECTED;
        Monitor monitor = MONITORS.getIfAbsentPutWithKey(monitorHandle, GlMonitor::new);
        var event = new MonitorEvent(monitor, connect);
        MonitorListeners.forEach(0, l -> l.monitorOnConnection(event));
        if (!connect) {
            MONITORS.remove(monitorHandle).close();
        }
    }
}
