package com.springboot.squaregames.game.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameCreationParams {
    private String gameType;
    private int playerCount;
    private int boardSize;

    public GameCreationParams() {}

    public GameCreationParams(String type, int playerCount, int boardSize) {
        this.gameType = type;
        this.playerCount = playerCount;
        this.boardSize = boardSize;
    }
}