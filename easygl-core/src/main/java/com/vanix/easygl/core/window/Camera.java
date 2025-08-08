package com.vanix.easygl.core.window;

import com.vanix.easygl.commons.value.FloatValue;
import com.vanix.easygl.commons.value.Value;
import com.vanix.easygl.core.Direction;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
    private final FloatValue speed;
    private final Vector3f pos;
    private final Vector3f up;
    private final Vector3f front;
    private Matrix4f view;
    private final FloatValue fov = Value.limited(45.f, 1.0f, 45.0f);

    public Camera(float speed, Vector3f pos, Vector3f up, Vector3f front) {
        this.speed = Value.of(speed);
        this.pos = pos;
        this.up = up;
        this.front = front;
        updateView();
    }

    public Camera() {
        this(2.5f,
                new Vector3f(0.0f, 0.0f, 3.0f),
                new Vector3f(0.0f, 1.0f, 0.0f),
                new Vector3f(0.0f, 0.0f, -1.0f));
    }

    private void updateView() {
        view = new Matrix4f().lookAt(this.pos, this.pos.clone().add(front), this.up);
        //System.out.println(String.format("pos=%s, front=%s, up=%s, view=%s", pos, front, up, view));
    }

    public Camera update(float pitch, float yaw) {
        float f = (float) Math.toRadians(pitch);
        float g = (float) Math.cos(f);
        float h = (float) Math.toRadians(yaw);
        this.front.set((float) Math.cos(h) * g, (float) Math.sin(f), (float) Math.sin(h) * g).normalize();
        updateView();
        return this;
    }

    public Camera move(Direction dir, float deltaTime) {
        float offset = speed.get() * deltaTime;
        switch (dir) {
            case Forward:
                this.pos.add(front.clone().mul(offset));
                break;
            case Backward:
                this.pos.sub(front.clone().mul(offset));
                break;
            case Left:
                this.pos.sub(front.clone().cross(up).normalize().mul(offset));
                break;
            case Right:
                this.pos.add(front.clone().cross(up).normalize().mul(offset));
                break;
            case Up:
                this.pos.add(up.clone().mul(offset));
                break;
            case Down:
                this.pos.sub(up.clone().mul(offset));
                break;
            default:
                break;
        }
        updateView();
        return this;
    }

    public Vector3f up() {
        return up;
    }

    public Vector3f front() {
        return front;
    }

    public FloatValue fov() {
        return fov;
    }

    public Vector3f position() {
        return pos;
    }

    public Matrix4f view() {
        return view;
    }

    public FloatValue speed() {
        return speed;
    }
}
