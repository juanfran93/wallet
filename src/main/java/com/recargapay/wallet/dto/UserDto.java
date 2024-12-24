package com.recargapay.wallet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Positive
    private Long du;

}
