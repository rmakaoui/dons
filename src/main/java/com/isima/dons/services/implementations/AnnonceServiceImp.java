package com.isima.dons.services.implementations;

import com.isima.dons.entities.Annonce;
import com.isima.dons.repositories.AnnonceRepository;
import com.isima.dons.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AnnonceServiceImp implements AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;

    public List<Annonce> getAllAnnonces() {
        return annonceRepository.getAnnoncesDisponible();
    }

    public List<Annonce> getAnnoncesByVendeurId(Long vendeurId) {
        return annonceRepository.findByVendeurId(vendeurId);
    }

    @Override
    public List<Annonce> getAnnoncesByUser(Long idUser) {
        return annonceRepository.getAnnoncesByUser(idUser);
    }

    public Annonce getAnnonceById(Long id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce not found"));
    }

    public Annonce createAnnonce(Annonce annonce) {
        annonce.setPri(false);
        return annonceRepository.save(annonce);
    }

    public Annonce updateAnnonce(Long id, Annonce updatedAnnonce) {
        Optional<Annonce> annonceOptional = annonceRepository.findById(id);

        if (annonceOptional.isPresent()) {
            Annonce existingAnnonce = annonceOptional.get();
            existingAnnonce.setTitre(updatedAnnonce.getTitre());
            existingAnnonce.setDescription(updatedAnnonce.getDescription());
            existingAnnonce.setEtatObjet(updatedAnnonce.getEtatObjet());
            existingAnnonce.setDatePublication(updatedAnnonce.getDatePublication());
            existingAnnonce.setZone(updatedAnnonce.getZone());
            existingAnnonce.setTypeDon(updatedAnnonce.getTypeDon());
            existingAnnonce.setKeywords(updatedAnnonce.getKeywords());
            return annonceRepository.save(existingAnnonce);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce not found");
        }
    }

    public void deleteAnnonce(Long id) {
        if (annonceRepository.existsById(id)) {
            annonceRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce not found");
        }
    }
}
