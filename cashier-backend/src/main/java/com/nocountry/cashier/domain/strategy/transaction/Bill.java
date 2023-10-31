package com.nocountry.cashier.domain.strategy.transaction;

import com.nocountry.cashier.controller.dto.request.BillRequestDTO;
import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.exception.InputNotValidException;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import com.nocountry.cashier.persistance.entity.BillEntity;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import com.nocountry.cashier.persistance.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.nocountry.cashier.util.Utility;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.strategy.transaction
 * @license Lrpa, zephyr cygnus
 * @since 25/10/2023
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class Bill extends Transaction{

    private final AccountRepository accountRepository;
    private final Utility utility;


    public EnumsTransactions getType() {
        return EnumsTransactions.PAYMENT;
    }

    @Override
    public TransactionEntity updateBalance(AccountEntity origin, AccountEntity destination, BigDecimal amount) {
        return null;
    }

    public BillEntity updateBalance(AccountEntity entity, BillRequestDTO data) {
        BigDecimal total = entity.getTotalAccount();
        BigDecimal amount= data.getAmount();
        if(total.compareTo(amount)<0){
            log.info("No se puede realizar el Pago, el monto es mayor al saldo");
            throw new InputNotValidException("No se puede realizar la transferencia, el monto es mayor al saldo");
        }
        //BigDecimal montoEjecutado = data.getAmount();
        total = total.subtract(amount);
        entity.setTotalAccount(total);
        accountRepository.save(entity);

        log.info("Ahora la cuenta Origen tiene $ {}" , entity.getTotalAccount());
        log.info("Pago realizado");
        return BillEntity.builder()
                .bill_type(data.getBill_type().toLowerCase())
                .bill_num(data.getBill_num())
                .amount(data.getAmount())
                .voucher_num(utility.generatorOTP(8))
                .accountEntity(entity)
                .dateEmit(LocalDateTime.now())
                .build();
    }
}
