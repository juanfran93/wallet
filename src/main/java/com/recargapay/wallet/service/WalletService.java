package com.recargapay.wallet.service;

import com.recargapay.wallet.dto.*;
import com.recargapay.wallet.exception.WalletException;

import java.time.LocalDate;

public interface WalletService {

    public void createWallet(UserDto dto) throws WalletException;

    CurrentBalanceDto getCurrentBalance(long du) throws WalletException;

    HistoricBalanceDto getBalanceByDate(long du, LocalDate date) throws WalletException;

    void deposit(MovementDto dto) throws WalletException;

    void withdraw(MovementDto dto) throws WalletException;

    void transfer(TransferDto dto) throws WalletException;
}
