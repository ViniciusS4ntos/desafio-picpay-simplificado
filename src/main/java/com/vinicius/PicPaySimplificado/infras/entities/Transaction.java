package com.vinicius.PicPaySimplificado.infras.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "transactions")
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private BigDecimal amount;


}
