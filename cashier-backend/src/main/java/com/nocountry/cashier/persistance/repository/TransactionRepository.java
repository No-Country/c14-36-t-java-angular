package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
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
public interface TransactionRepository extends JpaRepository<TransactionEntity,String> {

//  List<TransactionEntity> findByStateIs(EnumsState state);
//  List<TransactionEntity> findByStateEquals(EnumsState state);
  List<TransactionEntity> findByTypeIs(EnumsState state);
//  List<TransactionEntity> findByAmountLessThanEqual(String state);
   /* @Query(value = "SELECT t FROM TransactionEntity t WHERE  t.state = :state")
    List<TransactionEntity> findByStateContaining(@Param("state") EnumsState state);
*/

    @Query(value = "SELECT t FROM TransactionEntity t WHERE t.state = :state")
    List<TransactionEntity> findByState(@Param("state") EnumsState state);
//    @Query(value = "SELECT t FROM TransactionEntity t WHERE t.type = :type")
//    List<TransactionEntity> findByType(@Param("type_t") EnumsState type);

    @Query(value = "SELECT t FROM TransactionEntity t WHERE t.amount = :amount")
    List<TransactionEntity> findByType(@Param("amount")BigDecimal amount);




}
