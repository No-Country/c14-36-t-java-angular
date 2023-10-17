package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.persistance.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.persistance.repository
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByTokenGenerated(String token);
}
