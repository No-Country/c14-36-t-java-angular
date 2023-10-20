package com.nocountry.cashier.enums;

public enum EnumsTransactions {
    INCOME,
    EGRESS,
    TRANSFER,
    DEPOSIT,
    PAYMENT_QR;

    @Override
    public String toString() {
        return "EnumsTransactions{}";
    }
}
