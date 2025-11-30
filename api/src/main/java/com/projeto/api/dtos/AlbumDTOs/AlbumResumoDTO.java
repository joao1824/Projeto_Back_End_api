package com.projeto.api.dtos.AlbumDTOs;

import com.projeto.api.models.Album;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AlbumResumoDTO {

    //Atributos

    private String id;
    private String nome;
    private Integer total_faixas;
    private LocalDate lancamento;
    private String gravadora;
    private String perfil_spotify;
    private Integer popularidade;
    private Float nota_media;
    private List<String> imagens;
    private List<String> generos;
    

    //Construtor
    public AlbumResumoDTO(Album album) {
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
}
