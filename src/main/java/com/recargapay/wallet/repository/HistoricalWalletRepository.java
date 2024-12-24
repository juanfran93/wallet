package com.recargapay.wallet.repository;

import com.recargapay.wallet.model.HistoricalWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricalWalletRepository extends JpaRepository<HistoricalWallet, Long> {

    List<HistoricalWallet> findByDu(long du);
}
