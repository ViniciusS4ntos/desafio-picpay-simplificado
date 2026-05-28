package com.vinicius.PicPaySimplificado.Controller;

import com.vinicius.PicPaySimplificado.Controller.dtos.in.UserDTOIn;
import com.vinicius.PicPaySimplificado.Controller.dtos.out.UserDtoOut;
import com.vinicius.PicPaySimplificado.infras.entities.Transaction;
import com.vinicius.PicPaySimplificado.infras.entities.User;
import com.vinicius.PicPaySimplificado.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<UserDtoOut> criarUsuario(@RequestBody UserDTOIn user){
        User entity= userService.criarUsuario(mapper.map(user, User.class));
        return ResponseEntity.ok(mapper.map(entity, UserDtoOut.class));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> mostrarEnvios(@RequestParam("id") Integer id){
        return ResponseEntity.ok().body(userService.listarTransacoes(id));
    }

    @PostMapping("/dinheiro")
    public ResponseEntity<User> botarDinheiro(@RequestParam("balance")BigDecimal bigDecimal,
                                              @RequestParam("id") Integer id){
        return ResponseEntity.ok(userService.botarDinheiro(bigDecimal, id));
    }

}
