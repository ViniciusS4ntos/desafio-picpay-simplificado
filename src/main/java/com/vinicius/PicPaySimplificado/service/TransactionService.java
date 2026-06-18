package com.vinicius.PicPaySimplificado.service;

import com.vinicius.PicPaySimplificado.infras.entities.Transaction;
import com.vinicius.PicPaySimplificado.infras.entities.User;
import com.vinicius.PicPaySimplificado.infras.entities.enums.TypeUser;
import com.vinicius.PicPaySimplificado.infras.repositorys.TransactionRepository;
import com.vinicius.PicPaySimplificado.infras.repositorys.UserRepository;
import com.vinicius.PicPaySimplificado.infras.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserService userService;

    @Transactional
    public Transaction criarTransferencia(String token, BigDecimal balance, String cpf) {

        String tokenFormatado = token.substring(7);

        User sender = userRepository.findByEmail(jwtService.extractUsername(tokenFormatado));
        User received = userRepository.findByCpf(userService.formataCpf(cpf));

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(received);
        transaction.setAmount(balance);


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

        received.setBalance(received.getBalance().add(transaction.getAmount()));
        sender.setBalance(sender.getBalance().subtract(transaction.getAmount()));

        transactionRepository.save(transaction);
        return transaction;

    }




}
