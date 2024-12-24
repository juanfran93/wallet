package com.recargapay.wallet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CurrentBalanceDto {

    BigDecimal balance;
}
