package com.recargapay.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity(name = "HISTORICAL_WALLET")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class HistoricalWallet {

    @Id
    @GeneratedValue
    private long id;

    private long du;

    private BigDecimal balance;

    private LocalDate date;

    private LocalTime time;

}
