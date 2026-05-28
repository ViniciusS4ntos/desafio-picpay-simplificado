package com.vinicius.PicPaySimplificado.infras.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius.PicPaySimplificado.infras.entities.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Transaction> sentTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Transaction> receivedTransactions = new ArrayList<>();

    private BigDecimal balance;
}