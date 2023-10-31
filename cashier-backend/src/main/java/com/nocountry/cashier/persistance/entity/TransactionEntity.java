package com.nocountry.cashier.persistance.entity;

import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.persistance.entity.listener.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE transaction SET enabled=FALSE where id=?")
@Where(clause = "enabled=TRUE")
public class TransactionEntity extends Auditable<LocalDateTime> {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String id;
    @Column(name = "date_emit")
    private LocalDateTime dateEmit;
    //TRANSFER,PAYMENT
    @Enumerated(EnumType.STRING)
    @Column(name = "type_trans")
    private EnumsTransactions type;
    @Column(name="amount")
    private BigDecimal amount;

    @Column(name = "origin")
    private String origin; // String cvu

    @Column(name = "destination")
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EnumsState state;

    private Boolean enabled;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "idAccount")
    private AccountEntity accountEntity;

    @PrePersist
    public void onCreate() {
        this.setEnabled(Boolean.TRUE);
        this.dateEmit = LocalDateTime.now();
    }

}
