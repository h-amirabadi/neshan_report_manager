package com.neshan.controller;


import com.neshan.model.Account;
import com.neshan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.findAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }



    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestParam("email") String eMail, @RequestParam("password") String PW, Model model, HttpSession session) {
            Account account = null;
        try {
            account = accountService.authenticateAccount(eMail, PW);
            model.addAttribute("name", eMail);

            session.setAttribute("acc", account);
            return new ResponseEntity<>(account, HttpStatus.OK);
//            return "index";
        } catch (Exception ex) {
            System.out.println("Account not found!!");
            return new ResponseEntity<>(account, HttpStatus.EXPECTATION_FAILED);
//            return "/";
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) {
        Account account = accountService.findAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        Account acc = accountService.addAccount(account);
        return new ResponseEntity<>(acc, HttpStatus.CREATED);
    }



    @PutMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        Account acc = accountService.updateAccount(account);
        return new ResponseEntity<>(acc, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
