package com.isima.dons.services;

import com.isima.dons.entities.Recherche;

import java.util.List;

public interface RechercheService {

    List<Recherche> getAllRecherches();

    Recherche getRechercheById(Long id);

    Recherche createRecherche(Recherche recherche);

    Recherche updateRecherche(Long id, Recherche updatedRecherche);

    void deleteRecherche(Long id);
}
