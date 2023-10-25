package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.persistance.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.persistance.repository
 * @license Lrpa, zephyr cygnus
 * @since 9/10/2023
 */
public interface ImageRepository extends JpaRepository<ImageEntity, String> {
}
