package com.vetweb.gestor.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    @GetMapping({"/", "/index", "/home", "/inicio"})
    public String index() {
        return "index";
    }

    @GetMapping({"/login"})
    public String login(){
        String password = "password";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
        System.out.println("Hashed password: " + hashed);
        System.out.println("password: " + password);
        return "login";
    }
    
    
}
