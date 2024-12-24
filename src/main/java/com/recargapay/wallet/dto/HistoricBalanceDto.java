package com.recargapay.wallet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Builder
public class HistoricBalanceDto {

    LocalDate movementDate;
    LocalTime movementTime;
    BigDecimal balance;
}
