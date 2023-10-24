package com.nocountry.cashier.persistance.entity.listener.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.persistance.entity.listener.audit
 * @license Lrpa, zephyr cygnus
 * @since 9/10/2023
 */

@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class Auditable<T> {

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    //@Temporal(value = TemporalType.TIMESTAMP)
    protected T createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    //@Temporal(value = TemporalType.TIMESTAMP)
    protected T lastModifiedDate;

}
