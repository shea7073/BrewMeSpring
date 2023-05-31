package com.CS622.BrewMe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showLoginPage")
    // Precondition: Visit to site "/showLoginPage" route
    // Post condition: Return login template
    public String showLoginPage() {
        return "login";
    }
}
