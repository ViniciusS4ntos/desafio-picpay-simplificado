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

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public User criarUsuario(User user){

        // formata cpf
        user.setCpf(formataCpf(user.getCpf()));

        if (userRepository.existsByCpf(user.getCpf())){
            throw new RuntimeException("Cpf ja existente no banco");
        }

        if (user.getCpf().length() == 11){

        } else {
            throw new RuntimeException("Cpf invalido!");
        }

        if (!emailExistente(user.getEmail())){

            user.setBalance(BigDecimal.ZERO);
            user.setTypeUser(TypeUser.COMMOM);

            user.setPassword(passwordEncoder.encode(
                    user.getPassword()
            ));

            userRepository.save(user);

            return user;
        } else {
            throw new RuntimeException("Email ja existente no banco!");
        }
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

        return cpf.replaceAll("[^0-9]", "");
    }

    // VERIFICAR email existe no banco
    private Boolean emailExistente(String email){
        return userRepository.existsByEmail(email);
    }


}
