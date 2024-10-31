package com.isima.dons.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Groupe {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Annonce> annonces;


    @ManyToOne
    private User acheteur;

    public Groupe(Long id, List<Annonce> annonces, User acheteur) {
        this.id = id;
        this.annonces = annonces;
        this.acheteur = acheteur;
    }

    public Groupe() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }


    public void setAcheteur(User acheteur) {
        this.acheteur = acheteur;
    }
}

