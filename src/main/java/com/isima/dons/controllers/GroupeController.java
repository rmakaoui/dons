package com.isima.dons.controllers;

import com.isima.dons.entities.Groupe;
import com.isima.dons.services.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groupes")
public class GroupeController {

    private final GroupeService groupeService;

    @Autowired
    public GroupeController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @GetMapping
    public ResponseEntity<List<Groupe>> getAllGroupes() {
        return new ResponseEntity<>(groupeService.getAllGroupes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groupe> getGroupeById(@PathVariable Long id) {
        return new ResponseEntity<>(groupeService.getGroupeById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Groupe> createGroupe(@RequestBody Groupe groupe) {
        return new ResponseEntity<>(groupeService.createGroupe(groupe), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Groupe> updateGroupe(@PathVariable Long id, @RequestBody Groupe updatedGroupe) {
        return new ResponseEntity<>(groupeService.updateGroupe(id, updatedGroupe), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupe(@PathVariable Long id) {
        groupeService.deleteGroupe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
