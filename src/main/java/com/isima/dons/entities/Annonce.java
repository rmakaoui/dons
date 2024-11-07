package com.isima.dons.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private String description;

    public Annonce(Long id, String titre, String description, EtatObjet etatObjet, LocalDate datePublication, double latitude, double longitude, boolean typeDon, User vendeur, List<String> keywords) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.etatObjet = etatObjet;
        this.datePublication = datePublication;
        this.latitude = latitude;
        this.longitude = longitude;
        this.typeDon = typeDon;
        this.vendeur = vendeur;
        this.keywords = keywords;
    
    }

    public Annonce() {

    }

    public enum EtatObjet {
        Neuf, CommeNeuf, TresBonEtat, BonEtat, EtatCorrect, Occasion
    }


    @Enumerated(EnumType.STRING)
    private EtatObjet etatObjet;

    private LocalDate datePublication;

    private double latitude;

    private double longitude;

    private boolean typeDon;

    public void setVendeur(User vendeur) {
        this.vendeur = vendeur;
    }

    @ManyToOne
    private User vendeur;

    @ElementCollection
    private List<String> keywords;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtatObjet(EtatObjet etatObjet) {
        this.etatObjet = etatObjet;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(boolean typeDon) {
        this.typeDon = typeDon;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}

