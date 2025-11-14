package com.springboot.squaregames.catalog.controller;

import com.springboot.squaregames.catalog.interfaces.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RestController
@RequestMapping("/")
class GameCatalogController {
    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/catalog")
    public Collection<String> getGameIdentifiers()
    {
        return gameCatalog.getGameIdentifiers();
    }
}
