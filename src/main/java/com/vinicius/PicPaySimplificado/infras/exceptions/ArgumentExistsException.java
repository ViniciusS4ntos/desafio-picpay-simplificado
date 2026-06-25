package com.vinicius.PicPaySimplificado.infras.exceptions;

public class ArgumentExistsException extends RuntimeException {
    public ArgumentExistsException(String message) {
        super(message);
    }

    public ArgumentExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
