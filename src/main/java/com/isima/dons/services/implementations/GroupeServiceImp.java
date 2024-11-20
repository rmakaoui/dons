package com.isima.dons.services.implementations;

import com.isima.dons.configuration.UserPrincipale;
import com.isima.dons.entities.Annonce;
import com.isima.dons.entities.Groupe;
import com.isima.dons.entities.User;
import com.isima.dons.repositories.GroupeRepository;
import com.isima.dons.services.AnnonceService;
import com.isima.dons.services.GroupeService;
import com.isima.dons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupeServiceImp implements GroupeService {

    @Autowired
    private  GroupeRepository groupeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AnnonceService annonceService;


    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public Groupe getGroupeById(Long id) {
        return groupeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Groupe not found"));
    }

    @Override
    public Groupe createGroupe(Long annonceId, Long idUser) {
        User user = userService.getUserById(idUser);
        Annonce annonce = annonceService.getAnnonceById(annonceId);

        // Get any group that the user is part of and is not taken
        List<Groupe> existingGroups = groupeRepository.getGroupeByAcheteurAndNotTaken(idUser);

        // If no group exists, create a new group
        if (existingGroups.isEmpty()) {
            Groupe newGroupe = new Groupe();
            List<Annonce> annonces = new ArrayList<>();
            annonces.add(annonce);
            newGroupe.setAnnonces(annonces);
            newGroupe.setAcheteur(user);
            return groupeRepository.save(newGroupe);
        } else {
            // Add the annonce to the first group found (assuming one group per user)
            Groupe existingGroupe = existingGroups.get(0);
            List<Annonce> annoncesInGroup = existingGroupe.getAnnonces();

            // Check if the annonce already exists in the group
            if (!annoncesInGroup.contains(annonce)) {
                annoncesInGroup.add(annonce);
                existingGroupe.setAnnonces(annoncesInGroup);
                return groupeRepository.save(existingGroupe);
            } else {
                // If the annonce is already in the group, return the existing group
                return existingGroupe;
            }
        }
    }


    @Override
    public Groupe updateGroupe(Long id, Groupe updatedGroupe) {
        Optional<Groupe> groupeOptional = groupeRepository.findById(id);

        if (groupeOptional.isPresent()) {
            Groupe existingGroupe = groupeOptional.get();
            existingGroupe.setAnnonces(updatedGroupe.getAnnonces());
            existingGroupe.setAcheteur(updatedGroupe.getAcheteur());
            return groupeRepository.save(existingGroupe);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Groupe not found");
        }
    }

    @Override
    public void deleteGroupe(Long id) {
        if (groupeRepository.existsById(id)) {
            groupeRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Groupe not found");
        }
    }
}
