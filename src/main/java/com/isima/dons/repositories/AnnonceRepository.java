package com.isima.dons.repositories;

import com.isima.dons.entities.Annonce;
import com.isima.dons.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    @Query("select a from Annonce a where a.pri = false")
    List<Annonce> getAnnoncesDisponible();

    @Query("select a from Annonce a where a.vendeur.id = ?1")
    List<Annonce> getAnnoncesByUser(@Param("user") Long user);

}
