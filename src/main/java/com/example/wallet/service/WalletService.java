package com.example.wallet.service;

import com.example.wallet.exception.InvalidWalletBalanceException;
import com.example.wallet.exception.UnknownWalletException;
import com.example.wallet.model.Wallet;
import com.example.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet getWalletById(UUID id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new UnknownWalletException("Счет не найден"));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(WalletForm form) {
        walletRepository.findByWalletId(form.getWalletId())
                .ifPresentOrElse(wallet -> {
                    BigDecimal newBalance = switch (form.getOperationType()) {
                        case DEPOSIT -> wallet.getBalance().add(form.getAmount());
                        case WITHDRAW -> wallet.getBalance().subtract(form.getAmount());
                    };

                    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                        throw new InvalidWalletBalanceException("Недостаточно средств на счете");
                    }
                    wallet.setBalance(newBalance);

                    walletRepository.save(wallet);
                }, () -> {
                    throw new UnknownWalletException("Счет не найден");
                });
    }
}
