package com.vinicius.PicPaySimplificado.Controller.dtos.in;

import com.vinicius.PicPaySimplificado.infras.entities.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDTOIn {

    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String password;
    private TypeUser typeUser = TypeUser.COMMOM;
    private BigDecimal balance = BigDecimal.ZERO;

}
