package com.projeto.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Entity
public class Album {


    //Atributos

    @Id
    private String id;
    private String nome;
    private int total_faixas;
    private LocalDate lancamento;
    private String gravadora;
    private String perfil_spotify;
    private int popularidade;
    private float nota_media;

    @ElementCollection
    private List<String> imagens = new ArrayList<>();
    @ElementCollection
    private List<String> generos = new ArrayList<>();

    // @NotEmpty(message = ("artistas não pode estar vazia")) // revisar
    @ManyToMany
    @JoinTable(
            name = "album_artista",
            joinColumns = @JoinColumn(name = "id_album"),
            inverseJoinColumns = @JoinColumn(name = "id_artista")
    )
    @JsonIgnore
    private List<Artista> artistas = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    @JsonManagedReference("album-musicas")
    private List<Musica> musicas = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    @JsonManagedReference("album-reviews")
    private List<Review> reviews = new ArrayList<>();


    //Construtor

    public Album(){
        this.id = IdGerador.Gerar();
    }


    public Album(String nome, int total_faixas, LocalDate lancamento, String gravadora, String perfil_spotify, int popularidade,Float nota_media, List<String> imagens, List<String> generos, List<Artista> artistas, List<Musica> musicas, List<Review> reviews) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.total_faixas = total_faixas;
        this.lancamento = lancamento;
        this.gravadora = gravadora;
        this.perfil_spotify = perfil_spotify;
        this.popularidade = popularidade;
        this.nota_media = nota_media;
        this.imagens = imagens;
        this.generos = generos;
        this.artistas = artistas;
        this.musicas = musicas;
        this.reviews = reviews;
    }

    public Album(String nome, int total_faixas, LocalDate lancamento, String gravadora, String perfil_spotify, int popularidade) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.total_faixas = total_faixas;
        this.lancamento = lancamento;
        this.gravadora = gravadora;
        this.perfil_spotify = perfil_spotify;
        this.popularidade = popularidade;
    }

    public Album(String name, int totalFaixas, String releaseDate, String gravadora, String spotify, int popularidade) {
        this.id = IdGerador.Gerar();
        this.nome = name;
        this.total_faixas = totalFaixas;
        this.lancamento = LocalDate.parse(releaseDate);
        this.gravadora = gravadora;
        this.perfil_spotify = spotify;
        this.popularidade = popularidade;
        this.lancamento = LocalDate.parse(releaseDate);
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

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
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

    public float getNota_media() {
        return nota_media;
    }

    public void setNota_media(float nota_media) {
        this.nota_media = nota_media;
    }
}
