package com.recargapay.wallet.exception;

import com.recargapay.wallet.dto.ErrorDetailsDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(WalletException.class)
    public ResponseEntity<Object> manageWalletException(WalletException exception) {
        return new ResponseEntity<>(ErrorDetailsDto.builder().message(exception.getMessage()).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
