package com.example.wallet.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WalletForm {

    @NotNull
    private UUID walletId;
    @NotNull
    private OperationType operationType;
    @NotNull
    @Min(0)
    private BigDecimal amount;
}
