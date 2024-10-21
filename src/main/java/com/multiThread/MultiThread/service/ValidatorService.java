package com.multiThread.MultiThread.service;

import com.multiThread.MultiThread.entity.Account;
import com.multiThread.MultiThread.entity.Customer;
import com.multiThread.MultiThread.entity.Error;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ValidatorService {

    private final Validator validator ;

    public List<Error> validateCustomer(Customer customer){
        List<Error> errorList = new ArrayList<>();

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        Long customerId = customer.getCustomerId();  // Assuming this field exists in the Customer entity

        if (!isBirthDateValid(customer.getCustomerBirthDate())) {
            // Create an Error object for the invalid birth-date
            Error birthDateError = Error.builder()
                    .FILE_NAME("CustomerDataFile")
                    .RECORD_NUMBER(customerId)  // Use customer_id here
                    .ERROR_CODE("BIRTHDATE_INVALID")
                    .ERROR_CLASSIFICATION_NAME("Validation Error")
                    .ERROR_DESCRIPTION("BirthDate is null or before 1995")
                    .ERROR_DATE(LocalDate.now().toString())  // Example date
                    .build();

            // Add to error list
            errorList.add(birthDateError);
        }

        // Add constraint violations to error list
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Customer> violation : violations) {
                Error validationError = Error.builder()
                        .FILE_NAME("CustomerDataFile")
                        .RECORD_NUMBER(customerId)  // Use customer_id here
                        .ERROR_CODE("VALIDATION_ERROR")
                        .ERROR_CLASSIFICATION_NAME("Validation Error")
                        .ERROR_DESCRIPTION(violation.getMessage())
                        .ERROR_DATE(LocalDate.now().toString())  // Example date
                        .build();

                // Add each violation to error list
                errorList.add(validationError);
            }
        }
        // Optional: return the error list for further processing
        return errorList;
    }

    private boolean isBirthDateValid(String customerBirthDate) {
        // check if is it null
        if(customerBirthDate == null || customerBirthDate.isEmpty()){
            return false;
        }
        // format the code to check after 1994
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate cutoffDate = LocalDate.of(1995, 1, 1);
        try {
            LocalDate birthdate = LocalDate.parse(customerBirthDate, formatter);
            return birthdate.isAfter(cutoffDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public List<Error> validateAccount(Account account) {
        List<Error> errorList = new ArrayList<>();

        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        Long accountId = account.getId();

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Account> violation : violations) {
                Error validationError = Error.builder()
                        .FILE_NAME("AccountDataFile")
                        .RECORD_NUMBER(accountId)
                        .ERROR_CODE("VALIDATION_ERROR")
                        .ERROR_CLASSIFICATION_NAME("Validation Error")
                        .ERROR_DESCRIPTION(violation.getMessage())
                        .ERROR_DATE(LocalDate.now().toString())
                        .build();

                errorList.add(validationError);
            }
        }

        if (account.getAccountBalance() < account.getAccountLimit()) {
            Error balanceError = Error.builder()
                    .FILE_NAME("AccountDataFile")
                    .RECORD_NUMBER(accountId)
                    .ERROR_CODE("BALANCE_LIMIT_ERROR")
                    .ERROR_CLASSIFICATION_NAME("Validation Error")
                    .ERROR_DESCRIPTION("Balance must be greater than the account limit")
                    .ERROR_DATE(LocalDate.now().toString())
                    .build();

            errorList.add(balanceError);
        }

        if (account.getAccountType() == 0 || account.getAccountType() > 3) {
            Error accountTypeError = Error.builder()
                    .FILE_NAME("AccountDataFile")
                    .RECORD_NUMBER(accountId)
                    .ERROR_CODE("ACCOUNT_TYPE_ERROR")
                    .ERROR_CLASSIFICATION_NAME("Validation Error")
                    .ERROR_DESCRIPTION("Account type should be 1, 2, or 3")
                    .ERROR_DATE(LocalDate.now().toString())
                    .build();

            errorList.add(accountTypeError);
        }

        return errorList;
    }

}
