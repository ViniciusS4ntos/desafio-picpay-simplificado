package com.vinicius.PicPaySimplificado.Controller.dtos.in;

import com.vinicius.PicPaySimplificado.infras.entities.enums.TypeUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDTOIn {

    @NotNull(message = "O primeiro n0ome nao pode ser nulo")
    private String firstName;
    private String lastName;

    @NotNull(message = "O cpf nao pode ser nulo")
    private String cpf;

    @NotNull(message = "O email nao pode ser nulo")
    private String email;

    @NotNull(message = "A senha nao pode ser nula")
    private String password;

    private BigDecimal balance;

}
