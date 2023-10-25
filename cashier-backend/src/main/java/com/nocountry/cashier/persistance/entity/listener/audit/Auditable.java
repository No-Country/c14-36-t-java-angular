package com.nocountry.cashier.persistance.entity.listener.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nocountry.cashier.persistance.entity.UserEntity;
import jakarta.persistence.*;
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
//@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowGetters = true)
@Getter
@Setter
@NoArgsConstructor
public abstract class Auditable<T>{

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP", name = "created_date")
    //@Temporal(value = TemporalType.TIMESTAMP)
    protected T createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    //@Temporal(value = TemporalType.TIMESTAMP)
    protected T lastModifiedDate;

}
