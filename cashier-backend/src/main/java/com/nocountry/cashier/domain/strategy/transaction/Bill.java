package com.nocountry.cashier.domain.strategy.transaction;

import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.strategy.transaction
 * @license Lrpa, zephyr cygnus
 * @since 25/10/2023
 */
@Component
@Slf4j
public class Bill {

    public EnumsTransactions getType() {
        return EnumsTransactions.PAYMENT;
    }

    public AccountEntity updateBalance(AccountEntity entity, TransactionRequestDTO data) {
        BigDecimal total = entity.getTotalAccount();
        BigDecimal montoEjecutado = data.getAmount();
        total = total.add(montoEjecutado);
        entity.setTotalAccount(total);
        log.info("Deposito realizado");
        return entity;
    }
}
