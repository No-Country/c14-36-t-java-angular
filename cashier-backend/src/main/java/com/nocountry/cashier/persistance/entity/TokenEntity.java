package com.nocountry.cashier.persistance.entity;

import com.nocountry.cashier.persistance.entity.listener.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.persistance.entity
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
@Entity
@Table(name = "log_token")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TokenEntity extends Auditable<LocalDateTime> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String tokenGenerated;

}
