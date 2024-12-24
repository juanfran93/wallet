package com.recargapay.wallet.controller;

import com.recargapay.wallet.dto.*;
import com.recargapay.wallet.exception.WalletException;
import com.recargapay.wallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createWallet(@Valid @RequestBody UserDto dto) throws WalletException {
        walletService.createWallet(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/balance/{du}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrentBalanceDto> getCurrentBanance(@PathVariable(required = true) long du) throws WalletException {
        return ResponseEntity.ok(walletService.getCurrentBalance(du));
    }

    @GetMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoricBalanceDto> getBalanceBy(@RequestParam(name = "du") long du, @RequestParam(name = "date") LocalDate date) throws WalletException {
        return ResponseEntity.ok(walletService.getBalanceByDate(du, date));
    }

    @PostMapping(value = "/deposit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoricBalanceDto> deposit(@Valid @RequestBody MovementDto dto) throws WalletException {
        walletService.deposit(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/withdraw", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoricBalanceDto> withdraw(@Valid @RequestBody MovementDto dto) throws WalletException {
        walletService.withdraw(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoricBalanceDto> withdraw(@Valid @RequestBody TransferDto dto) throws WalletException {
        walletService.transfer(dto);
        return ResponseEntity.ok().build();
    }

}
