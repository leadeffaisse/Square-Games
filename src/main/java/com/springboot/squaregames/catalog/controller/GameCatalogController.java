package com.springboot.squaregames.catalog.controller;

import com.springboot.squaregames.catalog.interfaces.GameCatalog;
import com.springboot.squaregames.game.dto.GameInfo;
import com.springboot.squaregames.game.plugins.GamePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class GameCatalogController {

    @Autowired
    private List<GamePlugin> plugins;

    @GetMapping
    public Collection<GameInfo> getGameCatalog() {
        Locale locale = LocaleContextHolder.getLocale();  // Spring récupère automatiquement la locale

        return plugins.stream()
                .map(plugin -> new GameInfo(
                        plugin.getFactoryId(),
                        plugin.getName(locale)
                ))
                .collect(Collectors.toList());
    }
}