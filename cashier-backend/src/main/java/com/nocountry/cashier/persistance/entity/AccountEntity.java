package com.nocountry.cashier.persistance.entity;


import com.nocountry.cashier.persistance.entity.listener.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
//@Getter
//@Setter
@Data
@SQLDelete(sql = "UPDATE account_entity SET enabled=false where id=?")
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

    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "accountEntity")
    private List<TransactionEntity> transactionEntityList;


}
