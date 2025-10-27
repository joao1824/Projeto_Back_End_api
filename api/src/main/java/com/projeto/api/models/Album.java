package com.projeto.api.models;

import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Size(min = 27, max = 27, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar em vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @Positive(message = "total_faixas não pode ser negativa")
    private int total_faixas;

    @PastOrPresent(message = "Data de lancamento não pode estar no Futuro")
    private String lancamento;

    @Size(min = 0, max = 100,message = "a gravadora possui um tamanho não planejado")
    private String gravadora;

    @NotBlank(message = "O perfil_spotify não pode estar vazio")
    private String perfil_spotify;


    @Positive(message = "popularidade não pode ser negativa")
    private int popularidade;

    @DecimalMax(value = "100.0", message = "A nota media não pode ser maior que 10.0")
    @DecimalMin(value = "0.0", message = "A nota media não pode ser menor que 0.0")
    @Positive
    private float nota_media;


    @ElementCollection
    private List<String> imagens = new ArrayList<>();

    @NotEmpty(message = ("generos não pode estar vazia"))
    @ElementCollection
    private List<String> generos = new ArrayList<>();

    @NotEmpty(message = ("artistas não pode estar vazia"))
    @ManyToMany
    @JoinTable(
            name = "album_artista",
            joinColumns = @JoinColumn(name = "id_album"),
            inverseJoinColumns = @JoinColumn(name = "id_artista")
    )
    private List<Artista> artistas = new ArrayList<>();

    @NotEmpty(message = ("musicas não pode estar vazia"))
    @OneToMany(mappedBy = "album")
    private List<Musica> musicas = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    private List<Review> reviews = new ArrayList<>();


    //Construtor




    public Album(String nome, int total_faixas, String lancamento, String gravadora, String perfil_spotify, int popularidade,Float nota_media, List<String> imagens, List<String> generos, List<Artista> artistas, List<Musica> musicas, List<Review> reviews) {
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

    public float getNota_media() {
        return nota_media;
    }

    public void setNota_media(float nota_media) {
        this.nota_media = nota_media;
    }
}
