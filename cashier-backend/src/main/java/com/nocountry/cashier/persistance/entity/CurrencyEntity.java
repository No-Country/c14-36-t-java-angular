package com.nocountry.cashier.persistance.entity;

import com.nocountry.cashier.persistance.entity.listener.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.persistance.entity
 * @license Lrpa, zephyr cygnus
 * @since 26/10/2023
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "currency")
@AllArgsConstructor
public class CurrencyEntity extends Auditable<LocalDateTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Schema (example = "PE")
    private String codeCurrency;
    //@Schema (example = 3.54)
    private BigDecimal valueCurrency;

    private String symbol;
}
