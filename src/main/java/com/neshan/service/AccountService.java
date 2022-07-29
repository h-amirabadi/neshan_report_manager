package com.neshan.service;

import com.neshan.exception.AccountNotFoundException;
import com.neshan.model.Account;
import com.neshan.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepo accountRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account addAccount(Account account) {
        // add creation ts
        return accountRepo.save(account);
    }

    public Account authenticateAccount(String email, String PW) {
        return accountRepo.authenticateAccount(email, PW)
                .orElseThrow(() -> new AccountNotFoundException("Authentication failed."));
    }


    public List<Account> findAllAccounts() {
        return accountRepo.findAll();
    }

    public Account updateAccount(Account account) {
        return accountRepo.save(account);
    }


    public Account findAccountById(Long id) {
        return accountRepo.findAccountById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account by id " + id + " was not found"));
    }

    public void deleteAccount(Long id) {
        accountRepo.deleteAccountById(id);
    }
}
