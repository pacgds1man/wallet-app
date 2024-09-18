package com.example.wallet.exception;

public class InvalidWalletBalanceException extends RuntimeException {

    public InvalidWalletBalanceException(String message) {
        super(message);
    }
}
