package com.springboot.squaregames.game.plugins;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;
import java.util.OptionalInt;

@Component
public class TicTacToePlugin implements GamePlugin {

    @Value("${game.tictactoe.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.tictactoe.default-board-size}")
    private int defaultBoardSize;

    @Autowired
    private MessageSource messageSource;

    private final TicTacToeGameFactory factory = new TicTacToeGameFactory();

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.tictactoe.name", null, locale);
    }

    @Override
    public Game createGame(OptionalInt playerCount, OptionalInt boardSize) {
        int players = playerCount.orElse(defaultPlayerCount);
        int size = boardSize.orElse(defaultBoardSize);
        return factory.createGame(players, size);
    }

    @Override
    public String getFactoryId() {
        return factory.getGameFactoryId();
    }
}