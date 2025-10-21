package com.projeto.api.dtos;

import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import com.projeto.api.models.Musica;
import com.projeto.api.models.Review;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AlbumDTO {
    //Atributos

    @Max(value = 27, message = "ID não é valido pois possui um tamanho não planejado")
    @Min(value = 27, message = "ID não é valido pois possui um tamanho não planejado")
    @NotBlank(message = "ID não pode estar em vazio")
    @NotNull(message = "O id não pode ser nulo")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;


    @NotBlank(message = "total_faixas não pode estar em branco")
    @Positive(message = "total_faixas não pode ser negativa")
    @NotNull(message = "total_faixas não pode ser nulo")
    private int total_faixas;

    @PastOrPresent(message = "Data de lancamento não pode estar no Futuro")
    @NotBlank(message = "O lancamento não pode estar vazio")
    @NotNull(message = "O lancamento não pode ser nulo")
    private String lancamento;

    @Size(min = 0, max = 100,message = "a gravadora possui um tamanho não planejado")
    @NotBlank(message = "A gravadora não pode estar vazio")
    @NotNull(message = "A gravadora não pode ser nulo")
    private String gravadora;

    @NotBlank(message = "O perfil_spotify não pode estar vazio")
    @NotNull(message = "o perfil_spotify não pode ser nulo")
    private String perfil_spotify;


    @NotBlank(message = "popularidade não pode estar em branco")
    @Positive(message = "popularidade não pode ser negativa")
    @NotNull(message = "popularidade não pode ser nulo")
    private int popularidade;

    @NotNull(message = ("imagens não pode ser nula"))
    @NotEmpty(message = ("imagens não pode estar vazia"))
    private List<String> imagens = new ArrayList<>();

    @NotNull(message = ("generos não pode ser nula"))
    @NotEmpty(message = ("generos não pode estar vazia"))
    private List<String> generos = new ArrayList<>();

    @NotNull(message = ("artistas não pode ser nula"))
    @NotEmpty(message = ("artistas não pode estar vazia"))
    private List<Artista> artistas = new ArrayList<>();

    @NotNull(message = ("musicas não pode ser nula"))
    @NotEmpty(message = ("musicas não pode estar vazia"))
    private List<Musica> musicas = new ArrayList<>();

    @NotNull(message = ("reviews não pode ser nula"))
    @NotEmpty(message = ("reviews não pode estar vazia"))
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
