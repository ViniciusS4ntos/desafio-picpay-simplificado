package com.vinicius.PicPaySimplificado.Controller.dtos.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTOout {

    private Integer id;
    private Integer sender;
    private Integer receiver;
    private BigDecimal amount;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

}
