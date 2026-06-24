package com.vinicius.PicPaySimplificado.Controller.dtos.in;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserLoginDTO {

    @NotNull(message = "o email nao pode ser nulo")
    private String email;

    @NotNull(message = "a senha nao pode ser nula")
    private String passsword;

}
