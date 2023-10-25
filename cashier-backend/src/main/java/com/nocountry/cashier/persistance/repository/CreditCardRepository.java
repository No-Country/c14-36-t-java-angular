package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.persistance.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, String> {
}
