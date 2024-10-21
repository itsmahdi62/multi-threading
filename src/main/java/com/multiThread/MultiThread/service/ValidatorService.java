package com.multiThread.MultiThread.service;

import com.multiThread.MultiThread.entity.Account;
import com.multiThread.MultiThread.entity.Customer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

@Service
@AllArgsConstructor
public class ValidatorService {

    private final Validator validator ;

    public String validateCustomer(Customer customer){
        String listOfErrors = "";

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        if(!isBirthDateValid(customer.getCustomerBirthDate())){
            listOfErrors += "BirthDate is null of or before 1995 #\n";
        }

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Customer> violation : violations) {
                listOfErrors += violation.getMessage() + " \n#";
            }
        }
        return listOfErrors;
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


    public String validateAccount(Account account) {
        String listOfErrors = "";
        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Account> violation : violations) {
                listOfErrors += violation.getMessage() + "\n#";
            }
        }

        if (account.getAccountBalance() < account.getAccountLimit()){
            listOfErrors += "Balance must be greater than limit #\n";
        }

        if (account.getAccountType() == 0 || account.getAccountType() > 3) {
            listOfErrors += "Account type should be type 1 , 2 or 3 , #\n";
        }
        return listOfErrors;
    }
}
