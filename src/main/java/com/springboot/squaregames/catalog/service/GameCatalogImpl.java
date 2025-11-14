package com.springboot.squaregames.catalog.service;

import com.springboot.squaregames.catalog.interfaces.GameCatalog;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;

import java.util.Collection;
import java.util.List;

public class GameCatalogImpl implements GameCatalog {
    private TicTacToeGameFactory ticTacToeGameFactory;

    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of();
    }
}