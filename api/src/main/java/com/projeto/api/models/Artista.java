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

    public Artista(String nome, int popularidade, int seguidores, String perfil_spotify) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.popularidade = popularidade;
        this.seguidores = seguidores;
        this.perfil_spotify = perfil_spotify;
    }


    //Geters e Setters

}
