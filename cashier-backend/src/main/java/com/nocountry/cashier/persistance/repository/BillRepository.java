package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.persistance.entity.BillEntity;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface BillRepository  extends JpaRepository<BillEntity,String> {
    @Meta(comment = "Factura Segun su Id  y el Id de la cuenta")
    @Query(value = "SELECT b FROM BillEntity b WHERE b.id = :id AND b.accountEntity.idAccount= :id_account")
    BillEntity findOneByIdAccount(@Param("id")String id, @Param("id_account") String id_account);

    @Meta(comment = "Facturas Segun su Id  y el  Id de la cuenta")
    @Query(value = "SELECT b FROM BillEntity b WHERE b.accountEntity.idAccount = :id_account")
    Page<BillEntity> findAllByIdAccount(@Param("id_account") String id_account, Pageable pageable);

//    @Meta(comment = "Facturas Segun su tipo  y el Id de la cuenta")
//    @Query(value = "SELECT b FROM BillEntity b WHERE b.bill_type = :type AND b.accountEntity.idAccount = :id_account")
//            //,countQuery = "SELECT COUNT(t) FROM TransactionEntity t WHERE t.type = :type AND t.accountEntity.idAccount = :id_account")
//    Page<BillEntity> findByType(@Param("bill_type") String bill_type, @Param("id_account") String id_account, Pageable pageable);

    @Meta(comment = "Facturas Segun su typo y el Id de la cuenta")
    @Query(value = "SELECT b FROM BillEntity b WHERE b.bill_type = :bill_type AND b.accountEntity.idAccount = :id_account")
    Page<BillEntity> findByType(@Param("bill_type") String bill_type, @Param("id_account") String id_account, Pageable pageable);

    @Meta(comment = "Facturas Segun su monto  y el Id de la cuenta")
    @Query(value = "SELECT b FROM BillEntity b WHERE b.amount = :amount AND b.accountEntity.idAccount = :id_account")
            //countQuery = "SELECT COUNT(t) FROM TransactionEntity t WHERE t.amount = :type AND t.accountEntity.idAccount = :id_account")
    Page<BillEntity> findByAmount(@Param("amount") BigDecimal amount, @Param("id_account") String id_account, Pageable pageable);

}
