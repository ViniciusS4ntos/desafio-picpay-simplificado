package com.vinicius.PicPaySimplificado.Controller.dtos.in;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TransactionDTOin {

        private Integer sender;
        private Integer receiver;
        private BigDecimal amount;

    }
