package com.vinicius.PicPaySimplificado.infras.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GolbalHandlerException {


    @ExceptionHandler(ArgumentExistsException.class)
    public ResponseEntity<StandardError> argumentExistsException(ArgumentExistsException e,
                                                                 HttpServletRequest request) {
        StandardError standardError = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(standardError);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> businessException(BusinessException e,
                                                           HttpServletRequest request){
        StandardError standardError = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(standardError);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e,
                                                                 HttpServletRequest request){
        StandardError standardError = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(standardError);
    }

    @ExceptionHandler(UnauthorizedTransaction.class)
    public ResponseEntity<StandardError> unauthorizedTransaction(UnauthorizedTransaction e,
                                                                 HttpServletRequest request){
        StandardError standardError = new StandardError(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
    }
}
