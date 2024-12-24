package com.recargapay.wallet.dto;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class TransferDto {

    @Positive
    private long sourceDu;

    @Positive
    private long targetDu;

    @Positive
    private BigDecimal amount;
}
