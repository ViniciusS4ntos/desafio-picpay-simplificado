package com.vinicius.PicPaySimplificado.service;

import com.vinicius.PicPaySimplificado.infras.entities.Transaction;
import com.vinicius.PicPaySimplificado.infras.entities.User;
import com.vinicius.PicPaySimplificado.infras.entities.enums.TypeUser;
import com.vinicius.PicPaySimplificado.infras.exceptions.BusinessException;
import com.vinicius.PicPaySimplificado.infras.exceptions.ObjectNotFoundException;
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

    @Transactional
    public Transaction criarTransferencia(String token, BigDecimal balance, String cpf) {

        String tokenFormatado = token.substring(7); // tira o [Bearer ]
        String cpfFormt = cpf.replaceAll("[^0-9]", "");

        if (!userRepository.existsByEmail(jwtService .extractUsername(tokenFormatado))){
            throw new ObjectNotFoundException("Email nao encontrado!");
        }
        if (!userRepository.existsByCpf(cpfFormt)){
            throw new ObjectNotFoundException("Cpf nao encontrado!");
        }

        User sender = userRepository.findByEmail(jwtService.extractUsername(tokenFormatado));
        User received = userRepository.findByCpf(cpfFormt);

        Transaction transaction = new Transaction(
                sender.getId(),
                received.getId(),
                balance
        );

        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException ("O valor deve ser maior que 0");
        }

        if (transaction.getAmount().compareTo(sender.getBalance()) > 0) {
            throw new BusinessException ("Você não possui saldo suficiente!");
        }

        if (sender.getTypeUser().equals(TypeUser.SELLER)){
            throw new BusinessException ("Apenas usuarios do tipo COMMOM podem fazer transferencias! ");
        }

        if (transaction.getSender().equals(transaction.getReceiver())){
            throw new BusinessException("Voce nao pode fazer um pix para voce mesmo!");
        }

        received.setBalance(received.getBalance().add(transaction.getAmount()));
        sender.setBalance(sender.getBalance().subtract(transaction.getAmount()));

        return transactionRepository.save(transaction);

    }




}
