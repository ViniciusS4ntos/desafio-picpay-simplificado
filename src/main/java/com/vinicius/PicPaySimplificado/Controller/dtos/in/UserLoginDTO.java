package com.vinicius.PicPaySimplificado.Controller.dtos.in;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserLoginDTO {

    private String email;
    private String passsword;

}
