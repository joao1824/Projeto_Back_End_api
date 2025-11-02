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
    @Size(min = 22, max = 22, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @Positive(message = "popularidade não pode ser negativa")
    private int popularidade;

    @Positive(message = "seguidores não pode ser negativa")
    private int seguidores;

    @NotBlank(message = "O perfil não pode estar vazio")
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

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }
}
