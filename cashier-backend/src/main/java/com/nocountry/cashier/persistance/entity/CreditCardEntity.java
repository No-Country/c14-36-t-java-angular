package com.nocountry.cashier.persistance.entity;

import com.nocountry.cashier.persistance.entity.listener.audit.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE credit_card_entity SET enabled=false where id=?")
@Where(clause = "enabled=true")
public class CreditCardEntity extends Auditable<LocalDateTime> {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idCard;

    @Column(unique = true, length = 16)
    private String cardNumber;

    private String cardName;

    private LocalDate expirationDate;

    private String securityCode;

    private Boolean enabled;
}
