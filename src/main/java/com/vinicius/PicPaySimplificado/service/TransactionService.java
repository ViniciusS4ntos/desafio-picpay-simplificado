package com.vinicius.PicPaySimplificado.service;

import com.vinicius.PicPaySimplificado.infras.entities.Transaction;
import com.vinicius.PicPaySimplificado.infras.entities.User;
import com.vinicius.PicPaySimplificado.infras.entities.enums.TypeUser;
import com.vinicius.PicPaySimplificado.infras.repositorys.TransactionRepository;
import com.vinicius.PicPaySimplificado.infras.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Transaction criarTransferencia(Transaction transaction) {

        User receveid = userRepository.findById(transaction.getReceiver()).orElseThrow(() -> new RuntimeException("id nao encontrado"));
        User sender = userRepository.findById(transaction.getSender()).orElseThrow(() -> new RuntimeException("id nao encontrado"));

        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor deve ser maior que 0");
        }

        if (transaction.getAmount().compareTo(sender.getBalance()) > 0) {
            throw new RuntimeException("Você não possui saldo suficiente!");
        }

        if (sender.getTypeUser().equals(TypeUser.SELLER)){
            throw new RuntimeException("Apenas usuarios do tipo COMMOM podem fazer transferencias! ");
        }

        if (transaction.getSender().equals(transaction.getReceiver())){
            throw new RuntimeException("Voce nao pode fazer um pix para voce mesmo!");
        }

        receveid.setBalance(receveid.getBalance().add(transaction.getAmount()));
        sender.setBalance(sender.getBalance().subtract(transaction.getAmount()));

        transactionRepository.save(transaction);
        return transaction;

    }




}
