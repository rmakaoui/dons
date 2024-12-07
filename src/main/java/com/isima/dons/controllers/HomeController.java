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

import java.util.ArrayList;
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

    @GetMapping("/mes-annonces")
    public String mesAnnonces(Model model, Authentication authentication) {
        UserPrincipale userPrincipale = (UserPrincipale) authentication.getPrincipal();
        User user = userService.getUserById(userPrincipale.getId());
        System.out.println(user.getUsername());
        System.out.println("the user ID "+user.getId());
        List<Annonce> annonces = annonceService.getAnnoncesByUser(user.getId()); // Fetch annonces
        model.addAttribute("annonces", annonces); // Pass annonces to the model
        model.addAttribute("content", "pages/mes-annonces");
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
        System.out.println("the user ID "+user.getId());
        List<Groupe> groupe = groupeRepository.getGroupeByAcheteurAndNotTaken(user.getId());
        List<Annonce> annonces = new ArrayList<>();
        if (!groupe.isEmpty()){
            annonces = groupeRepository.getGroupeByAcheteurAndNotTaken(user.getId()).get(0).getAnnonces();
            model.addAttribute("groupe", groupe.get(0));
            model.addAttribute("annonces", annonces); // Pass annonces to the model
            model.addAttribute("content", "pages/groupe");
            return "home";
        }else{
            return this.home(model,authentication);
        }

    }

    @GetMapping("/valide")
    public String valide(Model model,Authentication authentication) {
        System.out.println("validééééééééé");
        UserPrincipale userPrincipale = (UserPrincipale) authentication.getPrincipal();
        User user = userService.getUserById(userPrincipale.getId());
        System.out.println(user.getUsername());
        List<Groupe> groupes = groupeRepository.getGroupeByAcheteurAndTaken(user.getId());
        model.addAttribute("groupes", groupes); // Pass annonces to the model
        model.addAttribute("content", "pages/valideGroup");
        return "home";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("content", "pages/contact");
        return "home";
    }

    @GetMapping("/annonce/add")
    public String addAnnoce(Model model) {
        model.addAttribute("content", "pages/add-annonce");
        return "home";
    }
}

