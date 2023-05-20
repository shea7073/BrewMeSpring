package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String home(Model model){
        String usertype = "";
        model.addAttribute("userType", usertype);
        return "index";
    }

    @GetMapping("/register/{userType}")
    public String registrationForm(Model model) {
       return "/register";
    }





}
