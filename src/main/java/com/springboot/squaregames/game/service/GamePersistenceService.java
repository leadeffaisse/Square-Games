package com.springboot.squaregames.game.service;

import com.springboot.squaregames.game.entity.GameEntity;
import com.springboot.squaregames.game.repository.GameJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GamePersistenceService {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    // CREATE - Sauvegarder un nouveau jeu
    public GameEntity saveGame(String gameId, int boardSize) {
        GameEntity gameEntity = new GameEntity(gameId, boardSize);
        return gameJpaRepository.save(gameEntity);
    }

    // READ - Récupérer tous les jeux
    public List<GameEntity> getAllGames() {
        return gameJpaRepository.findAll();
    }

    // READ - Récupérer un jeu par son ID
    public Optional<GameEntity> getGameById(Long id) {
        return gameJpaRepository.findById(id);
    }

    // READ - Récupérer un jeu par son gameId (UUID)
    public Optional<GameEntity> getGameByGameId(String gameId) {
        return gameJpaRepository.findByGameId(gameId);
    }

    // UPDATE - Mettre à jour un jeu existant
    public GameEntity updateGame(Long id, int newBoardSize) {
        Optional<GameEntity> optionalGame = gameJpaRepository.findById(id);
        if (optionalGame.isPresent()) {
            GameEntity game = optionalGame.get();
            game.setBoardSize(newBoardSize);
            return gameJpaRepository.save(game);
        }
        throw new RuntimeException("Jeu non trouvé avec l'id: " + id);
    }

    // DELETE - Supprimer un jeu par son ID
    public void deleteGame(Long id) {
        gameJpaRepository.deleteById(id);
    }

    // DELETE - Supprimer un jeu par son gameId
    @Transactional
    public void deleteGameByGameId(String gameId) {
        gameJpaRepository.deleteByGameId(gameId);
    }
}