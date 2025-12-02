package com.springboot.squaregames.game.repository;

import com.springboot.squaregames.game.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {

    // Spring Data JPA génère automatiquement l'implémentation !
    // findAll(), findById(), save(), deleteById() sont déjà disponibles

    // Méthode personnalisée : Spring génère le SQL automatiquement
    Optional<GameEntity> findByGameId(String gameId);

    // Méthode pour supprimer par gameId
    void deleteByGameId(String gameId);
}