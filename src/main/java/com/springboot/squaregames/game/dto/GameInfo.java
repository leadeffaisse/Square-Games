package com.springboot.squaregames.game.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameInfo {
    private String gameId;
    private String gameName;

    public GameInfo(String gameId, String gameName) {
        this.gameId = gameId;
        this.gameName = gameName;
    }
}
