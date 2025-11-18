package com.springboot.squaregames.game;

import com.springboot.squaregames.game.dto.GameCreationParams;

public interface GameService {
    String createGame(GameCreationParams params);
    Object getGame(String gameId);
}
