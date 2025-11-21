package com.springboot.squaregames.game;

import com.springboot.squaregames.catalog.interfaces.GameCatalog;
import com.springboot.squaregames.game.dto.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameCatalog gameCatalog;

    private Map<String, GameFactory> factories = new HashMap<>();

    public GameServiceImpl() {
        TicTacToeGameFactory ticTacToeFactory = new TicTacToeGameFactory();
        factories.put(ticTacToeFactory.getGameFactoryId(), ticTacToeFactory);

        ConnectFourGameFactory connectFourFactory = new ConnectFourGameFactory();
        factories.put(connectFourFactory.getGameFactoryId(), connectFourFactory);

        TaquinGameFactory taquinFactory = new TaquinGameFactory();
        factories.put(taquinFactory.getGameFactoryId(), taquinFactory);
    }

    @Override
    public String createGame(GameCreationParams params) {
        GameFactory factory = factories.get(params.getGameType());

        if (factory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type de jeu inconnu : " + params.getGameType());
        }

        Game game = factory.createGame(params.getPlayerCount(), params.getBoardSize());
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
