package com.isima.dons.repositories;

import com.isima.dons.entities.Recherche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechercheRepository extends JpaRepository<Recherche, Long> {
}
