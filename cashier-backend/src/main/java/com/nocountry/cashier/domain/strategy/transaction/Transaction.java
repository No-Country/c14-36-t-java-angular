package com.nocountry.cashier.domain.strategy.transaction;

import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import com.nocountry.cashier.persistance.entity.BillEntity;
import com.nocountry.cashier.persistance.entity.TransactionEntity;

import java.math.BigDecimal;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.strategy.transaction
 * @license Lrpa, zephyr cygnus
 * @since 25/10/2023
 */
public abstract class Transaction {

    public abstract EnumsTransactions getType();
    public abstract TransactionEntity updateBalance(AccountEntity origin, AccountEntity destination, BigDecimal amount);


}
