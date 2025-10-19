package com.projeto.api.models;


import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Artista {

    //Atributos

    @Id
    private String id = IdGerador.Gerar();
    private String nome;
    private int popularidade;
    private int seguidores;
    public String perfil_spotify;

    @ElementCollection
    private List<String> generos = new ArrayList<>();
    @ElementCollection
    private List<String> imagens = new ArrayList<>();

    @ManyToMany(mappedBy = "artistas")
    private List<Album> albuns = new ArrayList<>();


    //Construtor


    public Artista(String nome, int popularidade, int seguidores, String perfil_spotify, List<String> generos, List<String> imagens, List<Album> albuns) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.popularidade = popularidade;
        this.seguidores = seguidores;
        this.perfil_spotify = perfil_spotify;
        this.generos = generos;
        this.imagens = imagens;
        this.albuns = albuns;
    }

    public Artista(String nome, int popularidade, int seguidores, String perfil_spotify) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.popularidade = popularidade;
        this.seguidores = seguidores;
        this.perfil_spotify = perfil_spotify;
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

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }
}
