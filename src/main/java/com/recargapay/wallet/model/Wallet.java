package com.recargapay.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "WALLET")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private long du;
    private BigDecimal balance;
    private LocalDate creationDate;

    private LocalTime creationTime;
}
