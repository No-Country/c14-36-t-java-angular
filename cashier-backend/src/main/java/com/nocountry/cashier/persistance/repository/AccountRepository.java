package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.persistance.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
}
