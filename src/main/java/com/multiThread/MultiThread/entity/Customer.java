package com.multiThread.MultiThread.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SequenceGenerator(sequenceName = "server1_orcl_seq", name = "ps", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity(name="customer")
@Table(name="customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(generator = "ps")
    private Long id;

    @NotNull(message = "Customer id is Empty !")
    private Long customerId;

    @NotBlank(message = "Customer name is Empty !")
    private String customerName;

    @NotBlank(message = "Customer surname is Empty !")
    private String customerSurname;

    @NotBlank(message = "Customer address is Empty !")
    private String customerAddress;

    @NotNull(message = "Customer zipcode is Empty  !")
    private Long customerZipCode;

    @NotBlank(message = "Customer nationalId is Empty !")
    @Pattern(regexp = "^\\d{10}$"  , message = "Customer National ID must be 10 digits .")
    private String customerNationalId;

    @NotBlank(message = "Customer birthdate is Empty !")
    private String customerBirthDate;

}
