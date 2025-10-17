package com.projeto.api.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class Artista {

    //Atributos

    @Id
    private String id;
    private String nome;
    private ArrayList<String> generos;
    private int popularidade;
    private int seguidores;
    private ArrayList<String> imagens;
    public String perfil_spotify;

    //Construtores

    public Artista(String nome, ArrayList<String> generos, int popularidade, int seguidores, ArrayList<String> imagens, String perfil_spotify) {
        this.nome = nome;
        this.generos = generos;
        this.popularidade = popularidade;
        this.seguidores = seguidores;
        this.imagens = imagens;
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

    public ArrayList<String> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<String> generos) {
        this.generos = generos;
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

    public ArrayList<String> getImagens() {
        return imagens;
    }

    public void setImagens(ArrayList<String> imagens) {
        this.imagens = imagens;
    }

    public String getPerfil_spotify() {
        return perfil_spotify;
    }

    public void setPerfil_spotify(String perfil_spotify) {
        this.perfil_spotify = perfil_spotify;
    }
}
