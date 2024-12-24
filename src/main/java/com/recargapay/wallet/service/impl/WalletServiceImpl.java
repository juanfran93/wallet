package com.recargapay.wallet.service.impl;

import com.recargapay.wallet.dto.*;
import com.recargapay.wallet.exception.WalletException;
import com.recargapay.wallet.model.HistoricalWallet;
import com.recargapay.wallet.model.Wallet;
import com.recargapay.wallet.repository.HistoricalWalletRepository;
import com.recargapay.wallet.repository.WalletRepository;
import com.recargapay.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private HistoricalWalletRepository historicWalletRepository;

    @Override
    public void createWallet(UserDto dto) throws WalletException {
        Optional<Wallet> possibleWallet = walletRepository.findByDu(dto.getDu());

        if (possibleWallet.isPresent()) {
            throw new WalletException("Error: Already exist a wallet with that related DU.");
        }

        walletRepository.save(Wallet.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .du(dto.getDu())
                .creationDate(LocalDate.now())
                .creationTime(LocalTime.now())
                .balance(BigDecimal.ZERO)
                .build());
        log.info("Wallet for user DU: {} Created.", dto.getDu());
    }

    @Override
    public CurrentBalanceDto getCurrentBalance(long du) throws WalletException {
        Optional<Wallet> possibleWallet = walletRepository.findByDu(du);

        if (possibleWallet.isEmpty()) {
            throw new WalletException("Error: There are not a wallet with that related du.");
        }

        return CurrentBalanceDto.builder().balance(possibleWallet.get().getBalance()).build();
    }

    @Override
    public HistoricBalanceDto getBalanceByDate(long du, LocalDate date) throws WalletException {
        Wallet wallet = retrieveWallet(du);
        if (date.isBefore(wallet.getCreationDate())) {
        throw new WalletException("Given date is before that the wallet creation date.");
        }
        List<HistoricalWallet> movements = historicWalletRepository.findByDu(du);
        if (movements.size() == 0) {
            return HistoricBalanceDto.builder()
                    .movementDate(wallet.getCreationDate())
                    .movementTime(wallet.getCreationTime())
                    .balance(wallet.getBalance())
                    .build();
        }

        Optional<HistoricalWallet> lastMovement = movements.stream()
                .filter(m -> m.getDate().isEqual(date) || m.getDate().isBefore(date))
                .sorted((op1, op2) -> op2.getTime().compareTo(op1.getTime()))
                .findFirst();

        return HistoricBalanceDto.builder()
                .balance(lastMovement.get().getBalance())
                .movementTime(lastMovement.get().getTime())
                .movementDate(lastMovement.get().getDate())
                .build();
    }

    @Override
    public void deposit(MovementDto dto) throws WalletException {
        Wallet wallet = retrieveWallet(dto.getDu());

        BigDecimal balance = wallet.getBalance().add(dto.getAmount());
        updateWallet(wallet, balance);

    }

    @Override
    public void withdraw(MovementDto dto) throws WalletException {
        Wallet wallet = retrieveWallet(dto.getDu());
        if(wallet.getBalance().compareTo(dto.getAmount())==-1){
            throw new WalletException("That wallet does not have enough funds.");
        }

        BigDecimal balance = wallet.getBalance().subtract(dto.getAmount());
        updateWallet(wallet, balance);
    }

    @Override
    @Transactional
    public void transfer(TransferDto dto) throws WalletException {

        withdraw(MovementDto.builder()
                .du(dto.getSourceDu())
                .amount(dto.getAmount())
                .build());

        deposit(MovementDto.builder()
                .du(dto.getTargetDu())
                .amount(dto.getAmount())
                .build());

    }

    private Wallet retrieveWallet(Long du) throws WalletException {
        Optional<Wallet> possibleWallet = walletRepository.findByDu(du);

        if (possibleWallet.isEmpty()) {
            throw new WalletException("Error: There are not a wallet with that related du.");
        }
        return possibleWallet.get();
    }

    private void updateWallet(Wallet wallet, BigDecimal balance) {
        wallet.setBalance(balance);
        walletRepository.save(wallet);

        historicWalletRepository.save(HistoricalWallet.builder()
                .du(wallet.getDu())
                .balance(balance)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build());
    }

}
