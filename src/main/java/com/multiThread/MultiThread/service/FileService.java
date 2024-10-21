package com.multiThread.MultiThread.service;

import com.multiThread.MultiThread.entity.Account;
import com.multiThread.MultiThread.entity.Customer;
import com.multiThread.MultiThread.entity.Error;
import com.multiThread.MultiThread.repository.AccountRepository;
import com.multiThread.MultiThread.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileService {

    // autowired is not the best practice in springboot

    @Value("${customer.file.name}")
    private String customerFileName;

    @Value("${account.file.name}")
    private String accountFileName;

    private final ConvertorService convertorService;
    private final ValidatorService validatorService;
    private final ErrorService errorService ;
    private final CustomerRepository customerRepository ;
    private final AccountRepository accountRepository ;

    public FileService(ConvertorService convertorService, ValidatorService validatorService, ErrorService errorService, CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.convertorService = convertorService;
        this.validatorService = validatorService;
        this.errorService = errorService;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        System.out.println("FileService initialized");
    }

    @PostConstruct
    public void init() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(this::processCustomerFile);
        executorService.submit(this::processAccountFile);
        executorService.shutdown();
    }
    public void processCustomerFile() {
        System.out.println("Processing customer file...");
        try (InputStream inputStream = new FileInputStream(customerFileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                // convert each line to an entity
                Customer customer = convertorService.customerConverter(line);
                List<Error> errorList = validatorService.validateCustomer(customer);

                if(errorList.isEmpty()){
                    // do not forget to add encryption here
                    customerRepository.save(customer);
                }else {
                    errorService.writeErrors(errorList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processAccountFile() {
        System.out.println("Processing account file...");
        try (InputStream inputStream = new FileInputStream(accountFileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                Account account = convertorService.accountConvertor(line);
                List<Error> errorList = validatorService.validateAccount(account);
                if(errorList.isEmpty()){
                    // do not forget to add encryption here
                    accountRepository.save(account);
                }else {
                    errorService.writeErrors(errorList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

