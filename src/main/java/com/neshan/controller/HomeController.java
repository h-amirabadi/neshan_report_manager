package com.neshan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/home")
    public String homePage(){
        return "index";
    }


    @GetMapping(value = "/account")
    public String accountPage(){
        return "accounts";
    }
}
