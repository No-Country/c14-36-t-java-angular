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
@Table(name = "bill")
@SQLDelete(sql = "UPDATE bill SET enabled=false where id=?")
@Where(clause = "enabled=true")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class BillEntity extends Auditable<LocalDateTime> {
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
    @Column(name="type_bill")
    private String bill_type;
    @Column(name="bill_num")
    private String bill_num;
    @Column(name="amount")
    private BigDecimal amount;
    @Column(name="voucher_num")
    private String voucher_num;//para el numero utility generatorDTP
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EnumsState state;
    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "idAccount")
    private AccountEntity accountEntity;

    @PrePersist
    public void onCreate() {
        this.setEnabled(Boolean.TRUE);
    }
}
