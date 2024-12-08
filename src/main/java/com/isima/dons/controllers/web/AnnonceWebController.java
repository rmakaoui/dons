package com.isima.dons.controllers.web;

import java.net.URI;
import java.time.LocalDate;
import com.isima.dons.entities.Annonce;
import com.isima.dons.entities.User;
import com.isima.dons.services.AnnonceService;
import com.isima.dons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.isima.dons.configuration.UserPrincipale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/annonces")
public class AnnonceWebController {

    private final AnnonceService annonceService;
    private final UserService userService;

    @Autowired
    public AnnonceWebController(AnnonceService annonceService, UserService userService) {
        this.annonceService = annonceService;
        this.userService = userService;
    }

    @GetMapping
    public String getAll(Model model, Authentication authentication) {
        List<Annonce> annonces = annonceService.getAllAnnonces(); // Fetch annonces
        model.addAttribute("annonces", annonces); // Pass annonces to the model
        model.addAttribute("content", "pages/dashboard");
        return "home";
    }

    @GetMapping("/add")
    public String addAnnoce(Model model) {
        model.addAttribute("content", "pages/annonces/add-annonce");
        return "home";
    }

    @GetMapping("/mes-annonces")
    public String mesAnnonces(Model model, Authentication authentication) {
        UserPrincipale userPrincipale = (UserPrincipale) authentication.getPrincipal();
        User user = userService.getUserById(userPrincipale.getId());
        System.out.println(user.getUsername());
        System.out.println("the user ID " + user.getId());
        List<Annonce> annonces = annonceService.getAnnoncesByUser(user.getId()); // Fetch annonces
        model.addAttribute("annonces", annonces); // Pass annonces to the model
        model.addAttribute("content", "pages/annonces/mes-annonces");
        return "home";
    }

    @GetMapping("/{id}")
    public String getAnnonceDetails(@PathVariable Long id, Model model) {
        Annonce annonce = annonceService.getAnnonceById(id); // Méthode pour récupérer l'annonce par son ID
        model.addAttribute("annonce", annonce);
        model.addAttribute("content", "pages/annonces/annonce-details");
        return "home"; // Le nom de la page Thymeleaf à afficher
    }

    @GetMapping("/vendeur/{id}")
    public String getAnnoncesByVendeurId(@PathVariable Long id, Model model) {
        List<Annonce> annonces = annonceService.getAnnoncesByVendeurId(id);
        model.addAttribute("annonces", annonces);
        model.addAttribute("content", "pages/annonces/veundeur-annonces");
        return "home";
    }

    @PostMapping
    public String createAnnonce(@ModelAttribute Annonce annonce) {
        System.out.println("la zone : " + annonce.getZone());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipale userDetails = (UserPrincipale) authentication.getPrincipal();
        annonce.setDatePublication(LocalDate.now());
        annonce.setVendeur(userService.getUserById(userDetails.getId()));
        System.out.println(annonce.getKeywords());
        annonce.setKeywords(Arrays.asList(annonce.getKeywords().get(0).split(" ")));
        annonceService.createAnnonce(annonce);

        return "redirect:/annonces/mes-annonces";
    }

    @PutMapping("/{id}")
    public String updateAnnonce(@PathVariable Long id, @ModelAttribute Annonce updatedAnnonce) {
        Annonce annonce = annonceService.getAnnonceById(id);

        updatedAnnonce.setDatePublication(annonce.getDatePublication());
        updatedAnnonce.setVendeur(annonce.getVendeur());
        updatedAnnonce.setKeywords(
                Arrays.asList(updatedAnnonce.getKeywords().get(0)
                        .replaceAll("\\s+", " ")
                        .trim()
                        .split(" ")));

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonce(id);
        return "redirect:/";
    }

}
