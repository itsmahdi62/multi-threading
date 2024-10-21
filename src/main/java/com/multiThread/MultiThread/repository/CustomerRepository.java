package com.multiThread.MultiThread.repository;

import com.multiThread.MultiThread.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
