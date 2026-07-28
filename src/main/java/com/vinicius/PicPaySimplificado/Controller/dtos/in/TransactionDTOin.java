package com.vinicius.PicPaySimplificado.Controller.dtos.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TransactionDTOin {

        private Integer sender;
        private Integer receiver;
        private BigDecimal amount;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        private LocalDateTime date = LocalDateTime.now();

    }
