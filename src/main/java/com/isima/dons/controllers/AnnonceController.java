package com.isima.dons.controllers;
import java.time.LocalDate;
import com.isima.dons.entities.Annonce;
import com.isima.dons.entities.User;
import com.isima.dons.services.AnnonceService;
import com.isima.dons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.isima.dons.configuration.UserPrincipale;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;
    private final UserService userService;

    @Autowired
    public AnnonceController(AnnonceService annonceService,UserService userService) {
        this.annonceService = annonceService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Annonce>> getAllAnnonces() {
        return new ResponseEntity<>(annonceService.getAllAnnonces(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Annonce> getAnnonceById(@PathVariable Long id) {
        return new ResponseEntity<>(annonceService.getAnnonceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Annonce> createAnnonce(@ModelAttribute Annonce annonce) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipale userDetails = (UserPrincipale) authentication.getPrincipal();
        annonce.setDatePublication(LocalDate.now());
        annonce.setVendeur(userService.getUserById(userDetails.getId()));
        System.out.println(annonce.getKeywords());
        annonce.setKeywords(Arrays.asList(annonce.getKeywords().get(0).split(" ")));
        return new ResponseEntity<>(annonceService.createAnnonce(annonce), HttpStatus.CREATED);
    }


    
    @PutMapping("/{id}")
    public ResponseEntity<Annonce> updateAnnonce(@PathVariable Long id, @RequestBody Annonce updatedAnnonce) {
        return new ResponseEntity<>(annonceService.updateAnnonce(id, updatedAnnonce), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonce(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
}
