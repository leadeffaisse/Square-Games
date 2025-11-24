package com.springboot.squaregames.game.plugins;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.OptionalInt;

@Component
public class ConnectFourPlugin implements GamePlugin {

    @Value("${game.connect4.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.connect4.default-board-size}")
    private int defaultBoardSize;

    @Autowired
    private MessageSource messageSource;

    private final ConnectFourGameFactory factory = new ConnectFourGameFactory();

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.connect4.name", null, locale);
    }

    @Override
    public Game createGame(OptionalInt playerCount, OptionalInt boardSize) {
        return factory.createGame(
                playerCount.orElse(defaultPlayerCount),
                boardSize.orElse(defaultBoardSize)
        );
    }

    @Override
    public String getFactoryId() {
        return factory.getGameFactoryId();
    }
}