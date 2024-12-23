package com.isima.dons.services;

import com.isima.dons.entities.Annonce;

import java.util.List;

public interface AnnonceService {

    List<Annonce> getAllAnnonces();

    List<Annonce> getAnnoncesByUser(Long idUser);

    List<Annonce> getAnnoncesByVendeurId(Long vendeurId);

    Annonce getAnnonceById(Long id);

    Annonce createAnnonce(Annonce annonce);

    Annonce updateAnnonce(Long id, Annonce updatedAnnonce);

    void deleteAnnonce(Long id);
}
