package com.multiThread.MultiThread.repository;

import com.multiThread.MultiThread.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account , Long> {
}
