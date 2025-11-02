package com.projeto.api.dtos.ArtistasDTOs;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.models.Artista;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ArtistaDTO {

    //Atributos
    private String id;
    private String nome;
    private int popularidade;
    private int seguidores;
    public String perfil_spotify;
    private List<String> generos = new ArrayList<>();
    private String imagem;
    private List<AlbumResumoDTO> albuns = new ArrayList<>();

    //Construtor

    public ArtistaDTO(Artista artista) {
        this.id = artista.getId();
        this.nome = artista.getNome();
        this.popularidade = artista.getPopularidade();
        this.seguidores = artista.getSeguidores();
        this.perfil_spotify = artista.getPerfil_spotify();
        this.imagem = artista.getImagem();
        this.generos = artista.getGeneros();
        this.albuns = artista.getAlbuns().stream().map(AlbumResumoDTO::new).collect(Collectors.toList());
    }

    //Geters e Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(int popularidade) {
        this.popularidade = popularidade;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public String getPerfil_spotify() {
        return perfil_spotify;
    }

    public void setPerfil_spotify(String perfil_spotify) {
        this.perfil_spotify = perfil_spotify;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<AlbumResumoDTO> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<AlbumResumoDTO> albuns) {
        this.albuns = albuns;
    }
}
