package com.vinicius.PicPaySimplificado.infras.repositorys;

import com.vinicius.PicPaySimplificado.infras.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
