package com.multiThread.MultiThread.service;


import com.multiThread.MultiThread.entity.Account;
import com.multiThread.MultiThread.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class ConvertorService {
    // this class if converting lines of the file in to entities

    public Customer customerConverter(String entityLine){
        String[] fields = entityLine.split(",");

        // Record number is not needed, so we'll skip fields[0]
        Long customerId = Long.parseLong(fields[1]);
        String customerName = fields[2];
        String customerSurname = fields[3];
        String customerAddress = fields[4];
        Long customerZipCode = Long.parseLong(fields[6]);
        String customerBirthDate = fields[7];
        String customerNationalId = fields[8];

        return Customer.builder()
                .customerId(customerId)
                .customerName(customerName)
                .customerSurname(customerSurname)
                .customerAddress(customerAddress)
                .customerZipCode(customerZipCode)
                .customerBirthDate(customerBirthDate)
                .customerNationalId(customerNationalId)
                .build();
    }

    public Account accountConvertor(String line) {
        String[] fields = line.split(",");

        String accountNumber = fields[1];
        int accountType = Integer.parseInt(fields[2].trim());
        long customer_id = Long.parseLong(fields[3]);
        double accountLimit = Double.parseDouble(fields[4].replace("$", "").trim());
        String accountOpenDate = fields[5].trim();
        double accountBalanceDouble = Double.parseDouble(fields[6].replace("$", "").trim());

        return Account.builder()
                .accountNumber(accountNumber)
                .accountType(accountType)
                .accountLimit(accountLimit)
                .accountOpenDate(accountOpenDate)
                .accountBalance(accountBalanceDouble)
                .customerId(customer_id)
                .build();
    }

}
