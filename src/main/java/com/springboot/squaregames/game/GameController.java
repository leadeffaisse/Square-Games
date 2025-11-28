package com.springboot.squaregames.game;

import com.springboot.squaregames.game.dao.GameDAOImpl;
import com.springboot.squaregames.game.dto.GameCreationParams;
import com.springboot.squaregames.game.model.GameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameDAOImpl gameDAO;

    // Endpoints principaux
    @PostMapping
    public String createGame(@RequestBody GameCreationParams params) {
        return gameService.createGame(params);
    }

    @GetMapping("/{gameId}")
    public Object getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }

    // ========== ENDPOINTS DE TEST DAO ==========

    @GetMapping("/test/all")
    public Collection<GameModel> testGetAllGames() {
        return gameDAO.getAllGames();
    }

    @GetMapping("/test/{id}")
    public GameModel testGetById(@PathVariable Long id) {
        return gameDAO.getById(id);
    }

    @GetMapping("/test/create")
    public String testCreate() {
        String uuid = UUID.randomUUID().toString();
        gameDAO.save(uuid, 5);
        return "Jeu créé avec UUID: " + uuid;
    }

    @GetMapping("/test/delete/{id}")
    public String testDelete(@PathVariable Long id) {
        gameDAO.delete(id);
        return "Jeu supprimé: " + id;
    }

    @GetMapping("/test/update/{id}/{size}")
    public String testUpdate(@PathVariable Long id, @PathVariable int size) {
        gameDAO.update(id, size);
        return "Jeu mis à jour: id=" + id + ", nouvelle taille=" + size;
    }
}