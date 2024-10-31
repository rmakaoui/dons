package com.isima.dons.services.implementations;

import com.isima.dons.entities.Groupe;
import com.isima.dons.repositories.GroupeRepository;
import com.isima.dons.services.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GroupeServiceImp implements GroupeService {

    @Autowired
    private  GroupeRepository groupeRepository;


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
    public Groupe createGroupe(Groupe groupe) {
        return groupeRepository.save(groupe);
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
