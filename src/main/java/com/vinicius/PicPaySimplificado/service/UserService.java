package com.vinicius.PicPaySimplificado.service;

import com.vinicius.PicPaySimplificado.infras.entities.User;
import com.vinicius.PicPaySimplificado.infras.entities.enums.TypeUser;
import com.vinicius.PicPaySimplificado.infras.repositorys.TransactionRepository;
import com.vinicius.PicPaySimplificado.infras.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User criarUsuario(User user){

        // formata cpf
        user.setCpf(formataCpf(user.getCpf()));
        if (!emailExistente(user.getEmail())){

            user.setBalance(BigDecimal.ZERO);
            user.setTypeUser(TypeUser.COMMOM);

            userRepository.save(user);

            return user;
        } else {
            throw new RuntimeException("Email ja existente no banco!");
        }

    }


    // Formata CPF
    public String formataCpf(String cpf){

        String cpfFormatado = cpf.replaceAll("[^0-9]", "");

        if (userRepository.existsByCpf(cpfFormatado)){
            throw new RuntimeException("Cpf ja existente no banco");
        }

        if (cpfFormatado.length() == 11){
            return cpfFormatado;
        } else {
            throw new RuntimeException("Cpf invalido!");
        }

    }

    // VERIFICAR email existe no banco
    public Boolean emailExistente(String email){
        return userRepository.existsByEmail(email);
    }


}
