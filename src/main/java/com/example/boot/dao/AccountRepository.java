package com.example.boot.dao;

import com.example.boot.entities.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByNumber(String accountNumber);
}
