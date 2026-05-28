package com.vinicius.PicPaySimplificado.infras.repositorys;

import com.vinicius.PicPaySimplificado.infras.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


    //  SELECT
    //    sender.FIRST_NAME AS sender,
    //    t.AMOUNT AS amount,
    //    receiver.FIRST_NAME AS receiver,
    //    FORMATDATETIME(t.CREATED_AT, 'yyyy-MM-dd HH:mm:ss') AS horario
    //  FROM TRANSACTIONS t
    //  INNER JOIN USERS sender
    //      ON sender.ID = t.SENDER_ID
    //  INNER JOIN USERS receiver
    //      ON receiver.ID = t.RECEIVER_ID;


}
