package com.nocountry.cashier.enums;

public enum EnumsState {
    EARRING("EARRING"), DONE("DONE"), REJECTED("REJECTED");

    private final String state;

    EnumsState(String state) {
        this.state = state;
    }

    public String getName() {
        return state;
    }


}
