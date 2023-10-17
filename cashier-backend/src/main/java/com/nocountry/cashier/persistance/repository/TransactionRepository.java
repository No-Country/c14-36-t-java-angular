package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.persistance.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,String> {
    //@Query(("SELECT t from TransactionEntity t where t.dateEmit = :date"))
    //Optional<TransactionEntity> findByDate(@Param(value="dateEmit") Date date);
//    @Meta(comment = "Obtener todos los productos de una categoría específica")
//    @Query("SELECT t from TransactionEntity t where upper(t.type) = upper(:type)")
//    Optional<TransactionEntity> findByTypeTrans(@Param(value = "type") String type);
//    @Query("SELECT t from TransactionEntity t where upper(t.status) = upper(:status)")
//    Optional<TransactionEntity> findByStatusTrans(@Param(value = "status") String status);
//    @Query("SELECT t from TransactionEntity t where t.amount = :amount")
//    Optional<TransactionEntity> findByAmountTransactionEntity(@Param(value="amount") Long amount);
//
//    @Override
//    Optional<TransactionEntity> findById(String s);

    //Optional<TransactionEntity> findByTransactionByAmount(Long amount);
}
