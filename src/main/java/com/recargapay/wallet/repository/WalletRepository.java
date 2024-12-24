package com.recargapay.wallet.repository;

import com.recargapay.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    public Optional<Wallet> findByDu(Long du);
}
