package com.projeto.api.controller;

import com.projeto.api.models.Artista;
import com.projeto.api.service.AlbumService;

// dependencia spotify-web-api-java
import com.projeto.api.service.ArtistaService;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final AlbumService albumService;
    private final ArtistaService artistaService;

    public ApiController(AlbumService albumService, ArtistaService artistaService) {
        this.albumService = albumService;
        this.artistaService = artistaService;
    }

    @GetMapping("/artista")
    public Artista buscarArtista(@RequestParam String nome) {
        return artistaService.buscarArtista(nome);
    }

    @GetMapping("/album")
    public AlbumSimplified[] buscarAlbum(@RequestParam String nome) {
        return albumService.buscarAlbum(nome);
    }
}
