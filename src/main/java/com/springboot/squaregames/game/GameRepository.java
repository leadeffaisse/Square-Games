package com.springboot.squaregames.game;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GameRepository {

    private Map<String, Game> games = new HashMap<>();

    public Game save(String gameId, Game game) {
        games.put(gameId, game);
        return game;
    }

    public Game findById(String id) {
        return games.get(id);
    }
}
