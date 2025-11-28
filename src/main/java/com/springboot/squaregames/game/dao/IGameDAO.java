package com.springboot.squaregames.game.dao;

import com.springboot.squaregames.game.model.GameModel;
import java.util.Collection;

public interface IGameDAO {
    Collection<GameModel> getAllGames();
    GameModel getById(Long id);
    void save(String gameId, int boardSize);
    void update(Long id, int newBoardSize);
    void delete(Long id);
}