package com.vinicius.PicPaySimplificado.Controller.dtos.out;


public record AuthorizationResponse (
        String status,
        AuthorizationData data
){}
