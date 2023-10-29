package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.persistance.entity.BillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository  extends JpaRepository<BillsEntity,String> {



}
