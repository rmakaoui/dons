package com.isima.dons.services;

import com.isima.dons.entities.Groupe;

import java.util.List;

public interface GroupeService {

    List<Groupe> getAllGroupes();

    Groupe getGroupeById(Long id);

    Groupe createGroupe(Groupe groupe);

    Groupe updateGroupe(Long id, Groupe updatedGroupe);

    void deleteGroupe(Long id);
}
