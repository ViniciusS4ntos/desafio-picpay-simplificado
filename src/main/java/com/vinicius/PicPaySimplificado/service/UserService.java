package com.vinicius.PicPaySimplificado.service;

import com.vinicius.PicPaySimplificado.Controller.dtos.in.UserLoginDTO;
import com.vinicius.PicPaySimplificado.infras.entities.Transaction;
import com.vinicius.PicPaySimplificado.infras.entities.User;
import com.vinicius.PicPaySimplificado.infras.entities.enums.TypeUser;
import com.vinicius.PicPaySimplificado.infras.repositorys.TransactionRepository;
import com.vinicius.PicPaySimplificado.infras.repositorys.UserRepository;
import com.vinicius.PicPaySimplificado.infras.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public User criarUsuario(User user){

        // formata cpf
        user.setCpf(formataCpf(user.getCpf()));

        // verificar se ja existe o email
        if (emailExistente(user.getEmail())){
            throw new RuntimeException("Email ja existente no banco!");
        }

        user.setBalance(!Objects.equals(user.getBalance(), BigDecimal.ZERO) ? user.getBalance() : BigDecimal.ZERO);
        user.setTypeUser(TypeUser.COMMOM);

        user.setPassword(passwordEncoder.encode(
                user.getPassword()
        ));

        userRepository.save(user);

        return user;


    }

    public String logarUsuario(UserLoginDTO login){

        User user = userRepository.findByEmail(login.getEmail());

        if (passwordEncoder.matches(
                login.getPasssword(),
                user.getPassword()
        )){
            return jwtService.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Erro ao tentar entra na conta: ");
        }
    }

    public List<Transaction> listarTransacoes(String token){

        String tokenFormatado = token.replace("Bearer ", "");

        try{
            User user = userRepository.findByEmail(jwtService.extractUsername(tokenFormatado));
            return user.getSentTransactions();
        } catch (RuntimeException e){
            throw new RuntimeException("Erro ao tentar encontrar usuario: ", e.getCause());
        }
    }

    public User botarDinheiro(BigDecimal balance, String token){

        User user = userRepository.findByEmail(jwtService.extractUsername(token.substring(7)));

        user.setBalance(user.getBalance().add(balance));
        return userRepository.save(user);
    }


    // Formata CPF
    public String formataCpf(String cpf){

        String cpfFormt = cpf.replaceAll("[^0-9]", "");

        if (userRepository.existsByCpf(cpfFormt)){
            throw new RuntimeException("Cpf ja existente no banco");
        }

        if (cpfFormt.length() == 11){
            return cpfFormt;
        } else {
            throw new RuntimeException("Cpf invalido!");
        }
    }

    // VERIFICAR email existe no banco
    private Boolean emailExistente(String email){
        return userRepository.existsByEmail(email);
    }


}
