package com.nocountry.cashier.strategy.transaction;

import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.exception.InputNotValidException;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
public class Transfer extends Transaction {
    @Override
    public EnumsTransactions getType() {
        return EnumsTransactions.TRANSFER;
    }


    @Override
    public AccountEntity updateBalance(AccountEntity entity, AccountEntity B, TransactionRequestDTO data) {
        BigDecimal total = entity.getTotalAccount();
        BigDecimal montoEjecutado = data.getAmount();
        data.getOrigin();
        data.getDestination();
        if (total.compareTo(montoEjecutado) < 0){
            log.info("No se puede realizar la transferencia, el monto es mayor al saldo");
            throw new InputNotValidException("No se puede realizar la transferencia, el monto es mayor al saldo");
        }
        total = total.subtract(montoEjecutado);
        entity.setTotalAccount(total);
        log.info("Ahora la cuenta tiene $ {}" , entity.getTotalAccount());
        log.info("Transferencia realizada");
        return entity;
    }
}
