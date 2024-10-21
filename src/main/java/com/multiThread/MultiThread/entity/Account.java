package com.multiThread.MultiThread.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity(name="account")
@Table(name="account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(generator = "ps")
    private Long id;

    @NotBlank(message = "Account number is Empty ! ")
    @Pattern(regexp = "^\\d{22}$"  , message = "Account number must be 22 digits")
    private String accountNumber;

    @NotNull(message = "Account type is Empty !")
    private int accountType;

    @NotNull(message = "Account limit is Empty !")
    @Min(value = 1 , message = "Account limit must not be 0 !")
    private double accountLimit;

    @NotBlank(message = "Account open date is Empty !")
    private String accountOpenDate;

    @NotNull(message = "Account balance is Empty !")
    @Min(value = 1000 , message = "Account Balance must NOT be NULL!")
    private double accountBalance;


    private Long customerId ;
}
