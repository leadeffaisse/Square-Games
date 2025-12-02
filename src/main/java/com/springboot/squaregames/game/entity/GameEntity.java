package com.springboot.squaregames.game.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_id", nullable = false, unique = true)
    private String gameId;

    @Column(name = "board_size", nullable = false)
    private int boardSize;

    // Constructeur vide requis par JPA
    public GameEntity() {}

    // Constructeur avec paramètres
    public GameEntity(String gameId, int boardSize) {
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
        return "GameEntity{" +
                "id=" + id +
                ", gameId='" + gameId + '\'' +
                ", boardSize=" + boardSize +
                '}';
    }
}