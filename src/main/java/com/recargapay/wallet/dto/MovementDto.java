package com.recargapay.wallet.dto;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class MovementDto {

    @Positive
    private long du;

    @Positive
    private BigDecimal amount;
}
