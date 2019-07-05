package com.redhat.payment.service;

import java.util.StringJoiner;

public class Payment {

    public String amount;

    public String description;

    public Payment() {
    }

    public Payment(String amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Payment.class.getSimpleName() + "[", "]")
                .add("amount='" + amount + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
