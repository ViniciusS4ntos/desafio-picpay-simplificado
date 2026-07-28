package com.vinicius.PicPaySimplificado.infras.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
