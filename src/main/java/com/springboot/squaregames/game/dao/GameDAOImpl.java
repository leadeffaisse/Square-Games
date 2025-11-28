package com.springboot.squaregames.game.dao;

import com.springboot.squaregames.game.model.GameModel;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GameDAOImpl implements IGameDAO {

    private Connection conn;

    // Constructeur public pour Spring
    public GameDAOImpl(DataSource dataSource) {
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                System.out.println("Connected to MySQL/MariaDB!");
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Collection<GameModel> getAllGames() {
        List<GameModel> games = new ArrayList<>();
        String sql = "SELECT * FROM game";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet results = ps.executeQuery()) {

            while (results.next()) {
                GameModel gameModel = new GameModel();
                gameModel.setId(results.getLong("id"));
                gameModel.setGameId(results.getString("game_id"));
                gameModel.setBoardSize(results.getInt("board_size"));
                games.add(gameModel);
            }
        } catch (SQLException e) {
            System.err.println("Erreur getAllGames: " + e.getMessage());
            e.printStackTrace();
        }

        return games;
    }

    @Override
    public GameModel getById(Long id) {
        String sql = "SELECT * FROM game WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                GameModel gameModel = new GameModel();
                gameModel.setId(results.getLong("id"));
                gameModel.setGameId(results.getString("game_id"));
                gameModel.setBoardSize(results.getInt("board_size"));
                return gameModel;
            }
        } catch (SQLException e) {
            System.err.println("Erreur getById: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(String gameId, int boardSize) {
        String sql = "INSERT INTO game (game_id, board_size) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, gameId);
            ps.setInt(2, boardSize);
            int rows = ps.executeUpdate();
            System.out.println("Jeu créé, lignes affectées: " + rows);
        } catch (SQLException e) {
            System.err.println("Erreur save: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, int newBoardSize) {
        String sql = "UPDATE game SET board_size = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newBoardSize);
            ps.setLong(2, id);
            int rows = ps.executeUpdate();
            System.out.println("Jeu mis à jour, lignes affectées: " + rows);
        } catch (SQLException e) {
            System.err.println("Erreur update: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM game WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            System.out.println("Jeu supprimé, lignes affectées: " + rows);
        } catch (SQLException e) {
            System.err.println("Erreur delete: " + e.getMessage());
            e.printStackTrace();
        }
    }
}