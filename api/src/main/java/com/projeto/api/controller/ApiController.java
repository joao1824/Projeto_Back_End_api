package com.projeto.api.controller;

import com.projeto.api.models.Artista;
import com.projeto.api.service.ApiService;

// dependencia spotify-web-api-java
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/artista")
    public Artista[] buscarArtista(@RequestParam String nome) {
        return apiService.buscarArtista(nome);
    }

    @GetMapping("/album")
    public AlbumSimplified[] buscarAlbum(@RequestParam String nome) {
        return apiService.buscarAlbum(nome);
    }
}
