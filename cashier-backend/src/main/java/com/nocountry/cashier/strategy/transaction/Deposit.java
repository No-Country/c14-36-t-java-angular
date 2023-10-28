package com.nocountry.cashier.strategy.transaction;

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
public class Deposit extends Transaction {
    @Override
    public EnumsTransactions getType() {
        return EnumsTransactions.DEPOSIT;
    }

    @Override
    public AccountEntity updateBalance(AccountEntity entity, TransactionRequestDTO data) {
        BigDecimal total = entity.getTotalAccount();
        BigDecimal montoEjecutado = data.getAmount();
        total = total.add(montoEjecutado);
        entity.setTotalAccount(total);
        log.info("Deposito realizado");
        return entity;
    }
}
