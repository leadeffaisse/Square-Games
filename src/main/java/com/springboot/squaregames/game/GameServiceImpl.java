package com.springboot.squaregames.game;

import com.springboot.squaregames.catalog.interfaces.GameCatalog;
import com.springboot.squaregames.game.dto.GameCreationParams;
import com.springboot.squaregames.game.plugins.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private List<GamePlugin> plugins;

    private Map<String, GamePlugin> pluginMap = new HashMap<>();

    @PostConstruct
    public void init() {
        for (GamePlugin plugin : plugins) {
            String id = plugin.getFactoryId();
            pluginMap.put(id, plugin);
        }
    }

    @Override
    public String createGame(GameCreationParams params) {
        GamePlugin plugin = pluginMap.get(params.getGameType());

        if (plugin == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type de jeu inconnu : " + params.getGameType());
        }

        OptionalInt playerCount = params.getPlayerCount() > 0 ? OptionalInt.of(params.getPlayerCount()) : OptionalInt.empty();
        OptionalInt boardSize = params.getBoardSize() > 0 ? OptionalInt.of(params.getBoardSize()) : OptionalInt.empty();

        Game game = plugin.createGame(playerCount, boardSize);
        String gameId = UUID.randomUUID().toString();
        gameRepository.save(gameId, game);

        return gameId;
    }

    @Override
    public Object getGame(String gameId) {
        Game game = gameRepository.findById(gameId);
        return game;
    }
}
