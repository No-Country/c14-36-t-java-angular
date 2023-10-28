package com.nocountry.cashier.strategy.transaction;

import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.persistance.entity.AccountEntity;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.strategy.transaction
 * @license Lrpa, zephyr cygnus
 * @since 25/10/2023
 */
public abstract class Transaction {

    public abstract EnumsTransactions getType();

    public abstract AccountEntity updateBalance(AccountEntity entity, TransactionRequestDTO data);
}
