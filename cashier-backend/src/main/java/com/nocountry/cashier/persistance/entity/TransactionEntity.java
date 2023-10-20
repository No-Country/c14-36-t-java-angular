package com.nocountry.cashier.persistance.entity;

import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.persistance.entity.listener.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@SQLDelete(sql = "UPDATE transaction SET enabled=false where id=?")
//@Where(clause = "enabled=true")
public class TransactionEntity extends Auditable<LocalDateTime> {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String id;
    @Column(name = "date_emit")
    private LocalDateTime dateEmit;
    //INCOME,EGRESS,TRANSFER,DEPOSIT,PAYMENT_QR
    @Enumerated(EnumType.STRING)
    @Column(name = "type_trans")
    private EnumsTransactions type;
    @Column(name="amount")
    private BigDecimal amount;
    @Column(name = "origin")
    private String origin; // String cvu
    @Column(name = "destination")
    private String destination;
    //STATE WITH enums OR boolean?
    //private Boolean state;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EnumsState state;

    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private AccountEntity accountEntity;

}
