package com.isima.dons.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Recherche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getters and Setters
    @ManyToOne
    private User user;

    public Recherche(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Recherche() {

    }

    public void setUser(User user) {
        this.user = user;
    }
}

