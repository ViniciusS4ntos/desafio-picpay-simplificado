package com.vinicius.PicPaySimplificado.Controller;

import com.vinicius.PicPaySimplificado.Controller.dtos.in.TransactionDTOin;
import com.vinicius.PicPaySimplificado.Controller.dtos.out.TransactionDTOout;
import com.vinicius.PicPaySimplificado.infras.entities.Transaction;
import com.vinicius.PicPaySimplificado.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final ModelMapper mapper;


    @PostMapping("{cpf}")
    public ResponseEntity<TransactionDTOout> criarTransaction(@RequestHeader("Authorization") String token,
                                                              @RequestParam("balance") BigDecimal balance,
                                                              @PathVariable("cpf") String cpf){
        return ResponseEntity.ok().body(mapper.map(transactionService.criarTransferencia(token, balance, cpf), TransactionDTOout.class));
    }
}
