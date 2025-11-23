package com.projeto.api.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@AllArgsConstructor
@Entity
public class Artista {

    //Atributos

    @Id
    private String id;
    private String nome;
    private Integer popularidade;
    private Integer seguidores;
    public String perfil_spotify;
    @ElementCollection
    private List<String> generos = new ArrayList<>();
    private String imagem;
    @ManyToMany(mappedBy = "artistas")
    private List<Album> albuns = new ArrayList<>();


    //Construtor

    public Artista(){
        this.id = IdGerador.Gerar();
    }


    public Artista(String nome, int popularidade, int seguidores, String perfil_spotify, List<String> generos, String imagem, List<Album> albuns) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.popularidade = popularidade;
        this.seguidores = seguidores;
        this.perfil_spotify = perfil_spotify;
        this.generos = generos;
        this.imagem = imagem;
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

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }
}
