package com.nocountry.cashier.persistance.entity;


import jakarta.persistence.*;

import com.nocountry.cashier.persistance.entity.listener.audit.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
//@SQLDelete(sql = "UPDATE account_entity SET enabled=false where id=?")
//@Where(clause = "enabled=true")
public class AccountEntity extends Auditable<LocalDateTime> {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idAccount;

    @Column(unique = true, length = 25)
    private String cvu;

    private LocalDate openAccountDate;

    private LocalDate updateAccountDate;

    private BigDecimal totalAccount;

    private boolean status;

    private Boolean enabled;

    @OneToMany(mappedBy = "accountEntity")
    private List<TransactionEntity> transaccionEntityList;



}
