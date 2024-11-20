package com.isima.dons.repositories;

import com.isima.dons.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long> {

    @Query("select g from Groupe g where g.acheteur.id = ?1 and g.pri = false")
    List<Groupe> getGroupeByAcheteurAndNotTaken(@Param("achteur") Long achteur);
}
