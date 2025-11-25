package com.projeto.api.controller;

import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import com.projeto.api.models.Musica;
import com.projeto.api.service.AlbumService;

// dependencia spotify-web-api-java
import com.projeto.api.service.ArtistaService;
import com.projeto.api.service.MusicaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Track;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class ApiController {

    private final AlbumService albumService;
    private final ArtistaService artistaService;
    private final MusicaService musicaService;

    public ApiController(AlbumService albumService, ArtistaService artistaService, MusicaService musicaService) {
        this.albumService = albumService;
        this.artistaService = artistaService;
        this.musicaService = musicaService;
    }

    @GetMapping("/artista")
    public Artista buscarArtista(@RequestParam String nome) {
        return artistaService.buscarArtista(nome);
    }

    @GetMapping("/album")
    public Album buscarAlbum(@RequestParam String nome) {
        return albumService.buscarAlbum(nome);
    }

    @GetMapping("/musica")
    public Musica buscarMusica(@RequestParam String nome) {
        return musicaService.buscarMusica(nome);
    }
}
