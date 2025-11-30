package com.projeto.api.dtos.ArtistasDTOs;

import com.projeto.api.models.Artista;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class ArtistaResumoDTO {
    //Atributos
    private String id;
    private String nome;
    private Integer popularidade;
    private Integer seguidores;
    public String perfil_spotify;
    private List<String> generos = new ArrayList<>();
    private String imagem;

    //Construtor
    public ArtistaResumoDTO(Artista artista) {
        this.id = artista.getId();
        this.nome = artista.getNome();
        this.popularidade = artista.getPopularidade();
        this.seguidores = artista.getSeguidores();
        this.perfil_spotify = artista.getPerfil_spotify();
        this.imagem = artista.getImagem();
        this.generos = artista.getGeneros();
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

    public Integer getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(Integer popularidade) {
        this.popularidade = popularidade;
    }

    public Integer getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Integer seguidores) {
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
}
