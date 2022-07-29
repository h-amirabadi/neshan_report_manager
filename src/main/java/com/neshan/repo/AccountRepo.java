package com.neshan.repo;

import com.neshan.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    void deleteAccountById(Long id);

    @Query("SELECT a FROM Account a WHERE a.eMail = ?1 AND a.PW >= ?2")
    Optional<Account> authenticateAccount(String email, String pw);

    Optional<Account> findAccountById(Long id);
}
