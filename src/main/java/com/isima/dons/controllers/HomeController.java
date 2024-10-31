package com.isima.dons.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("welcomeMessage", "Welcome to Thymeleaf with Spring Boot!");
        return "home"; // This returns the template file home.html 
    }
}

