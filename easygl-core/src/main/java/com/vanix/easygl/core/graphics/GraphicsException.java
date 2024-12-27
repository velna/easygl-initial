package com.vanix.easygl.core.graphics;

public class GraphicsException extends RuntimeException {

    private static final long serialVersionUID = 1996016930671527347L;

    public GraphicsException(String message) {
        super(message);
    }

    public GraphicsException(String message, Throwable cause) {
        super(message, cause);
    }

}
