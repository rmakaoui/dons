package com.isima.dons.controllers;

import com.isima.dons.configuration.UserPrincipale;
import com.isima.dons.entities.Annonce;
import com.isima.dons.entities.Groupe;
import com.isima.dons.entities.User;
import com.isima.dons.repositories.GroupeRepository;
import com.isima.dons.services.AnnonceService;
import com.isima.dons.services.GroupeService;
import com.isima.dons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AnnonceService annonceService;

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        List<Annonce> annonces = annonceService.getAllAnnonces(); // Fetch annonces
        model.addAttribute("annonces", annonces); // Pass annonces to the model
        model.addAttribute("content", "pages/dashboard");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("content", "pages/about");
        return "home";
    }
    @GetMapping("/groupe")
    public String groupe(Model model,Authentication authentication) {
        UserPrincipale userPrincipale = (UserPrincipale) authentication.getPrincipal();
        User user = userService.getUserById(userPrincipale.getId());
        System.out.println(user.getUsername());
        List<Annonce> annonces = groupeRepository.getGroupeByAcheteurAndNotTaken(user.getId()).get(0).getAnnonces();
        model.addAttribute("annonces", annonces); // Pass annonces to the model
        model.addAttribute("content", "pages/groupe");
        return "grorupe";
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

