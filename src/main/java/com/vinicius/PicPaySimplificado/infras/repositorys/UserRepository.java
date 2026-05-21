package com.vinicius.PicPaySimplificado.infras.repositorys;

import com.vinicius.PicPaySimplificado.infras.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByEmail(String email);
    Boolean existsByCpf(String email);

}
