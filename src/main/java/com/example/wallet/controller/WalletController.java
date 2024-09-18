package com.example.wallet.controller;

import com.example.wallet.model.Wallet;
import com.example.wallet.service.WalletForm;
import com.example.wallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallets/{wallet_uuid}")
    public Wallet get(@PathVariable("wallet_uuid") UUID id) {
        return walletService.getWalletById(id);
    }

    @PostMapping("/wallet")
    public void update(@RequestBody @Valid WalletForm form) {
        walletService.update(form);
    }
}
