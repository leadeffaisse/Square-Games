package com.springboot.squaregames.game.controller;

import com.springboot.squaregames.game.entity.GameEntity;
import com.springboot.squaregames.game.service.GamePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GamePersistenceController {

    @Autowired
    private GamePersistenceService gamePersistenceService;

    // CREATE - Créer un nouveau jeu
    @PostMapping
    public ResponseEntity<GameEntity> createGame(@RequestParam int boardSize) {
        String gameId = UUID.randomUUID().toString();
        GameEntity savedGame = gamePersistenceService.saveGame(gameId, boardSize);
        return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
    }

    // READ - Récupérer tous les jeux
    @GetMapping
    public ResponseEntity<List<GameEntity>> getAllGames() {
        List<GameEntity> games = gamePersistenceService.getAllGames();
        return ResponseEntity.ok(games);
    }

    // READ - Récupérer un jeu par son ID
    @GetMapping("/{id}")
    public ResponseEntity<GameEntity> getGameById(@PathVariable Long id) {
        Optional<GameEntity> game = gamePersistenceService.getGameById(id);
        return game.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // READ - Récupérer un jeu par son gameId (UUID)
    @GetMapping("/uuid/{gameId}")
    public ResponseEntity<GameEntity> getGameByGameId(@PathVariable String gameId) {
        Optional<GameEntity> game = gamePersistenceService.getGameByGameId(gameId);
        return game.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE - Mettre à jour un jeu
    @PutMapping("/{id}")
    public ResponseEntity<GameEntity> updateGame(@PathVariable Long id, @RequestParam int boardSize) {
        try {
            GameEntity updatedGame = gamePersistenceService.updateGame(id, boardSize);
            return ResponseEntity.ok(updatedGame);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Supprimer un jeu par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gamePersistenceService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}