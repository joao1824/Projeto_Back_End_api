package com.projeto.api.dtos.AlbumDTOs;

import com.projeto.api.dtos.ArtistasDTOs.ArtistaResumoDTO;
import com.projeto.api.dtos.MusicaDTOs.MusicaResumoDTO;
import com.projeto.api.dtos.ReviewDTOs.ReviewResumoDTO;
import com.projeto.api.models.Album;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class AlbumDTO {
    //Atributos

    //Não é nescessário validar o id pois ele é gerado automaticamente quando passa pelo mapper, atraves da função id gerador
    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @PositiveOrZero(message = "total_faixas não pode ser negativa")
    private Integer total_faixas;

    @PastOrPresent(message = "Data de lancamento não pode estar no Futuro")
    private LocalDate lancamento;

    @Size(min = 0, max = 100,message = "a gravadora possui um tamanho não planejado")
    private String gravadora;

    private String perfil_spotify;


    @PositiveOrZero(message = "popularidade não pode ser negativa")
    private Integer popularidade;

    @PositiveOrZero
    @Max(value = 100, message = "não é valido pois possui um tamanho não planejado")
    @Min(value = 0, message = "não é valido pois possui um tamanho não planejado")
    private Float nota_media;

    @PositiveOrZero
    private Integer totalNotas = 0;   // soma de todas as notas

    @PositiveOrZero
    private Integer qtdReviews = 0;   // número total de reviews


    @ElementCollection
    private List<String> imagens = new ArrayList<>();

    @ElementCollection
    private List<String> generos = new ArrayList<>();
    private List<ArtistaResumoDTO> artistas = new ArrayList<>();
    private List<MusicaResumoDTO> musicas = new ArrayList<>();
    private List<ReviewResumoDTO> reviews = new ArrayList<>();


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

    public Integer getTotal_faixas() {
        return total_faixas;
    }

    public void setTotal_faixas(Integer total_faixas) {
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

    public Integer getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(Integer popularidade) {
        this.popularidade = popularidade;
    }

    public Float getNota_media() {
        return nota_media;
    }

    public void setNota_media(Float nota_media) {
        this.nota_media = nota_media;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public void setTotalNotas(Integer totalNotas) {
        this.totalNotas = totalNotas;
    }

    public Integer getQtdReviews() {
        return qtdReviews;
    }

    public void setQtdReviews(Integer qtdReviews) {
        this.qtdReviews = qtdReviews;
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

    public List<ArtistaResumoDTO> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<ArtistaResumoDTO> artistas) {
        this.artistas = artistas;
    }

    public List<MusicaResumoDTO> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicaResumoDTO> musicas) {
        this.musicas = musicas;
    }

    public List<ReviewResumoDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewResumoDTO> reviews) {
        this.reviews = reviews;
    }
}
