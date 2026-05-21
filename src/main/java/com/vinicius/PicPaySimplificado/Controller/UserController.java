package com.vinicius.PicPaySimplificado.Controller;

import com.vinicius.PicPaySimplificado.Controller.dtos.in.UserDTOIn;
import com.vinicius.PicPaySimplificado.Controller.dtos.out.UserDtoOut;
import com.vinicius.PicPaySimplificado.infras.entities.User;
import com.vinicius.PicPaySimplificado.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
