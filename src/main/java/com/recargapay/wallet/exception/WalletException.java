package com.recargapay.wallet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WalletException extends Throwable{

    private String message;
}
