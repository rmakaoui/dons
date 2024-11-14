package com.isima.dons.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("content", "pages/dashboard");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("content", "pages/about");
        return "home";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("content", "pages/contact");
        return "home";
    }

    @GetMapping("/annonce/add")
    public String addAnnoce(Model model) {
        //model.addAttribute("welcomeMessage", "Welcome to Thymeleaf with Spring Boot!");
        return "add-annonce"; // This returns the template file home.html 
    }
}

