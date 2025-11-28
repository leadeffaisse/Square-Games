package com.springboot.squaregames.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // Anotation JPA : classe = table en BDD
@Table(name = "players") // Nom de la table
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private Long id;

    @Column(nullable = false, unique = true) // Colonne non-null et unique
    private String username;

    @Column(nullable = false)
    private String email;

    private Integer score = 0; // Score par défaut

    public Player() {} // Obligatoirement vide pour JPA


    public Player(String username, String email) {
        this.username = username;
        this.email = email;
    }
}