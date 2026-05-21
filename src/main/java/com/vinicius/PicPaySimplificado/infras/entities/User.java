package com.vinicius.PicPaySimplificado.infras.entities;

import com.vinicius.PicPaySimplificado.infras.entities.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "Users")
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String firstName;

    @Column(length = 255, nullable = false)
    private String lastName;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    @Column(nullable = false)
    private BigDecimal balance;

}
