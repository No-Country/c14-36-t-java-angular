package com.nocountry.cashier.domain.strategy.transaction;

import com.nocountry.cashier.enums.EnumsTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.strategy.transaction
 * @license Lrpa, zephyr cygnus
 * @since 25/10/2023
 */
@Service
public final class TransactionStrategy {
    private final Map<EnumsTransactions, Transaction> transactions = new EnumMap<>(EnumsTransactions.class);
    @Autowired
    public TransactionStrategy(Set<Transaction> transactionSet) {
        transactionSet.forEach(transaction -> this.transactions.put(transaction.getType(), transaction));
    }

    public Transaction getTransaction(EnumsTransactions type){
        return transactions.get(type);
    }
}
