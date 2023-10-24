package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,String>{


//    @Query(value = "SELECT t FROM TransactionEntity t WHERE t.state = :state AND t.accountEntity.idAccount= :id_account")
//    List<TransactionEntity> findByState(@Param("state") EnumsState state, @Param("id_account") String id_account);
    @Query(value = "SELECT t FROM TransactionEntity t WHERE t.state = :state AND t.accountEntity.idAccount = :id_account",
            countQuery = "SELECT COUNT(t) FROM TransactionEntity t WHERE t.state = :state AND t.accountEntity.idAccount = :id_account")
    Page<TransactionEntity> findByState(@Param("state") EnumsState state, @Param("id_account") String id_account, Pageable pageable);
//    @Query(value = "SELECT t FROM TransactionEntity t WHERE t.type = :type")
//    List<TransactionEntity> findByType(@Param("type") EnumsState type);
@Query(value = "SELECT t FROM TransactionEntity t WHERE t.type = :type AND t.accountEntity.idAccount = :id_account",
        countQuery = "SELECT COUNT(t) FROM TransactionEntity t WHERE t.type = :type AND t.accountEntity.idAccount = :id_account")
Page<TransactionEntity> findByType(@Param("type") EnumsTransactions type, @Param("id_account") String id_account, Pageable pageable);
//    @Query(value = "SELECT t FROM TransactionEntity t WHERE t.amount = :amount")
//    List<TransactionEntity> findByType(@Param("amount")BigDecimal amount);
    @Query(value = "SELECT t FROM TransactionEntity t WHERE t.amount = :amount AND t.accountEntity.idAccount = :id_account",
            countQuery = "SELECT COUNT(t) FROM TransactionEntity t WHERE t.amount = :type AND t.accountEntity.idAccount = :id_account")
    Page<TransactionEntity> findByAmount(@Param("amount") BigDecimal amount, @Param("id_account") String id_account, Pageable pageable);




}
