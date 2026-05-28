package com.vinicius.PicPaySimplificado.Controller;

import com.vinicius.PicPaySimplificado.Controller.dtos.in.TransactionDTOin;
import com.vinicius.PicPaySimplificado.Controller.dtos.out.TransactionDTOout;
import com.vinicius.PicPaySimplificado.infras.entities.Transaction;
import com.vinicius.PicPaySimplificado.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final ModelMapper mapper;


    @PostMapping
    public ResponseEntity<TransactionDTOout> criarTransaction(TransactionDTOin transaction){
        return ResponseEntity.ok().body(mapper.map(transactionService.criarTransferencia(mapper.map(transaction, Transaction.class)), TransactionDTOout.class));
    }
}
