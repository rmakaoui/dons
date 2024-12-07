package com.isima.dons.controllers;

import com.isima.dons.configuration.UserPrincipale;
import com.isima.dons.entities.Annonce;
import com.isima.dons.entities.Groupe;
import com.isima.dons.entities.User;
import com.isima.dons.services.GroupeService;
import com.isima.dons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/groupes")
public class GroupeController {

    @Autowired
    private GroupeService groupeService;

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<Groupe>> getAllGroupes() {
        return new ResponseEntity<>(groupeService.getAllGroupes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groupe> getGroupeById(@PathVariable Long id) {
        return new ResponseEntity<>(groupeService.getGroupeById(id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllGroups() {
        groupeService.deleteAllGroups();
        return ResponseEntity.ok("All groups have been deleted successfully.");
    }

    @PostMapping
    public RedirectView createGroupe(@RequestParam("annonceId") Long annonceId, Authentication authentication) {
        System.out.println(annonceId);

        // Get the authenticated user
        UserPrincipale userPrincipale = (UserPrincipale) authentication.getPrincipal();
        User user = userService.getUserById(userPrincipale.getId());
        System.out.println(user.getUsername());

        // Create the groupe
        groupeService.createGroupe(annonceId, user.getId());

        // Redirect to /group after the group is created
        return new RedirectView("/groupe");
    }



    @PutMapping("/{id}")
    public ResponseEntity<Groupe> updateGroupe(@PathVariable Long id, @RequestBody Groupe updatedGroupe) {
        return new ResponseEntity<>(groupeService.updateGroupe(id, updatedGroupe), HttpStatus.OK);
    }

    @PostMapping("/validate")
    public RedirectView validateGroupe(@RequestParam Long groupeId) {
        Groupe groupe = groupeService.validateGroupe(groupeId);
        return new RedirectView("/valide");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupe(@PathVariable Long id) {
        groupeService.deleteGroupe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/remove")
    public RedirectView removeAnnonceFromGroupe(
            @RequestParam Long groupeId,
            @RequestParam Long annonceId) {
        System.out.println("groupeId: "+groupeId);
        System.out.println("annonceId: "+annonceId);
        boolean isRemoved = groupeService.removeAnnonceFromGroupe(groupeId, annonceId);

        if (isRemoved) {
            return new RedirectView("/groupe");
        } else {
            return null;
        }
    }
}
