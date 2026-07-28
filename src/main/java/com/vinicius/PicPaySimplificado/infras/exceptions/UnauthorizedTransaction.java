package com.vinicius.PicPaySimplificado.infras.exceptions;

public class UnauthorizedTransaction extends RuntimeException {
    public UnauthorizedTransaction(String message) {
        super(message);
    }
    public UnauthorizedTransaction(String message, Throwable throwable){super(message,throwable);}
}
