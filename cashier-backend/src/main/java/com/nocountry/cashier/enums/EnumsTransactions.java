package com.nocountry.cashier.enums;

public enum EnumsTransactions {
    TRANSFER("TRANSFER"),
    PAYMENT("PAYMENT");

    private final String type;

    EnumsTransactions(String type) {
        this.type = type;
    }

    public String getName() {
        return type;
    }
    @Override
    public String toString() {
        return "EnumsTransactions{}";
    }
}
