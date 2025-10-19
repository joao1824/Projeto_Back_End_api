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
public class Album {


    //Atributos

    @Id
    private String id = IdGerador.Gerar();
    private String nome;
    private int total_faixas;
    private String lancamento;
    private String gravadora;
    private String perfil_spotify;
    private int popularidade;

    @ElementCollection
    private List<String> imagens = new ArrayList<>();
    @ElementCollection
    private List<String> generos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "album_artista",
            joinColumns = @JoinColumn(name = "id_album"),
            inverseJoinColumns = @JoinColumn(name = "id_artista")
    )
    private List<Artista> artistas = new ArrayList<>();


    @OneToMany(mappedBy = "album")
    private List<Musica> musicas = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    private List<Review> reviews = new ArrayList<>();


    //Construtor


    public Album(String nome, int total_faixas, String lancamento, String gravadora,String perfil_spotify, int popularidade, List<Musica> musicas) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.total_faixas = total_faixas;
        this.lancamento = lancamento;
        this.gravadora = gravadora;
        this.perfil_spotify = perfil_spotify;
        this.popularidade = popularidade;
    }









    //Geters e Setters

}
