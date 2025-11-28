package com.springboot.squaregames.game.model;

public class GameModel {
    private Long id;
    private String gameId;
    private int boardSize;

    // Constructeur vide
    public GameModel() {}

    // Constructeur avec paramètres
    public GameModel(String gameId, int boardSize) {
        this.gameId = gameId;
        this.boardSize = boardSize;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    @Override
    public String toString() {
        return "GameModel{" +
                "id=" + id +
                ", gameId='" + gameId + '\'' +
                ", boardSize=" + boardSize +
                '}';
    }
}