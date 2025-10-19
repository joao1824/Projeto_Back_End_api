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


    public Album(String nome, int total_faixas, String lancamento, String gravadora, String perfil_spotify, int popularidade, List<String> imagens, List<String> generos, List<Artista> artistas, List<Musica> musicas, List<Review> reviews) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.total_faixas = total_faixas;
        this.lancamento = lancamento;
        this.gravadora = gravadora;
        this.perfil_spotify = perfil_spotify;
        this.popularidade = popularidade;
        this.imagens = imagens;
        this.generos = generos;
        this.artistas = artistas;
        this.musicas = musicas;
        this.reviews = reviews;
    }

    public Album(String nome, int total_faixas, String lancamento, String gravadora, String perfil_spotify, int popularidade) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.total_faixas = total_faixas;
        this.lancamento = lancamento;
        this.gravadora = gravadora;
        this.perfil_spotify = perfil_spotify;
        this.popularidade = popularidade;
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

    public int getTotal_faixas() {
        return total_faixas;
    }

    public void setTotal_faixas(int total_faixas) {
        this.total_faixas = total_faixas;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getGravadora() {
        return gravadora;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }

    public String getPerfil_spotify() {
        return perfil_spotify;
    }

    public void setPerfil_spotify(String perfil_spotify) {
        this.perfil_spotify = perfil_spotify;
    }

    public int getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(int popularidade) {
        this.popularidade = popularidade;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
