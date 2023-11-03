package com.nocountry.cashier.domain.strategy.transaction;

import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.exception.InputNotValidException;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import com.nocountry.cashier.persistance.entity.BillEntity;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import com.nocountry.cashier.persistance.repository.AccountRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.strategy.transaction
 * @license Lrpa, zephyr cygnus
 * @since 25/10/2023
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class Transfer extends Transaction {

    private final AccountRepository accountRepository;
    @Override
    public EnumsTransactions getType() {
        return EnumsTransactions.TRANSFER;
    }

    @Override
    public TransactionEntity updateBalance(AccountEntity origin, AccountEntity destination, BigDecimal amount) {
        BigDecimal balanceOrigin = origin.getTotalAccount().setScale(2, RoundingMode.HALF_UP);
        BigDecimal balanceDestiny = destination.getTotalAccount().setScale(2, RoundingMode.HALF_UP);

        if (balanceOrigin.compareTo(amount) < 0){
            log.info("No se puede realizar la transferencia, el monto es mayor al saldo");
            throw new InputNotValidException("No se puede realizar la transferencia, el monto es mayor al saldo");
        }
        amount = amount.setScale(2, RoundingMode.HALF_UP);
        origin.setTotalAccount(balanceOrigin.subtract(amount));
        destination.setTotalAccount(balanceDestiny.add(amount));

        accountRepository.saveAll(List.of(origin, destination));
        log.info("Ahora la cuenta Origen tiene $ {}" , origin.getTotalAccount());
        log.info("Ahora la cuenta Destino tiene $ {}" , destination.getTotalAccount());
        log.info("Transferencia realizada");
        return TransactionEntity.builder()
                .accountEntity(origin)
                .origin(origin.getCvu())
                .destination(destination.getUserEntity().getName()+ " " + destination.getUserEntity().getLastName())
                .amount(amount)
                .build();
    }


}
