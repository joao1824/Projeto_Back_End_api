package com.projeto.api.dtos;

import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import com.projeto.api.models.Musica;
import com.projeto.api.models.Review;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AlbumDTO {
    //Atributos

    private String id;
    private String nome;
    private int total_faixas;
    private LocalDate lancamento;
    private String gravadora;
    private String perfil_spotify;
    private int popularidade;
    private Float nota_media;
    private List<String> imagens = new ArrayList<>();
    private List<String> generos = new ArrayList<>();
    private List<Artista> artistas = new ArrayList<>();
    private List<Musica> musicas = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();


    //Construtor
    public AlbumDTO(Album album) {
        this.id = album.getId();
        this.nome = album.getNome();
        this.total_faixas = album.getTotal_faixas();
        this.lancamento = album.getLancamento();
        this.gravadora = album.getGravadora();
        this.perfil_spotify = album.getPerfil_spotify();
        this.popularidade = album.getPopularidade();
        this.nota_media = album.getNota_media();
        this.imagens = album.getImagens();
        this.generos = album.getGeneros();
        this.artistas = album.getArtistas();
        this.musicas = album.getMusicas();
        this.reviews = album.getReviews();
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

    public Float getNota_media() {
        return nota_media;
    }

    public void setNota_media(Float nota_media) {
        this.nota_media = nota_media;
    }
}
