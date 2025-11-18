package com.springboot.squaregames.game;

import com.springboot.squaregames.game.dto.GameCreationParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public String createGame(@RequestBody GameCreationParams params) {
        return gameService.createGame(params);
    }

    @GetMapping("/{gameId}")
    public Object getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }
}