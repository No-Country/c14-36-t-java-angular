package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.persistance.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.persistance.repository
 * @license Lrpa, zephyr cygnus
 * @since 27/10/2023
 */
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
}
