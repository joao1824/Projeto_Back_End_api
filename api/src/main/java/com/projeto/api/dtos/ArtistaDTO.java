package com.projeto.api.dtos;

import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import com.projeto.api.util.IdGerador;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ArtistaDTO {

    //Atributos


    @Size(min = 27, max = 27, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @NotBlank(message = "popularidade não pode estar em branco")
    @Positive(message = "popularidade não pode ser negativa")
    private int popularidade;

    @NotBlank(message = "seguidores não pode estar em branco")
    @Positive(message = "seguidores não pode ser negativa")
    @NotNull(message = "seguidores não pode ser nulo")
    private int seguidores;

    @NotBlank(message = "O perfil não pode estar vazio")
    public String perfil_spotify;

    @NotNull(message = ("generos não pode ser nula"))
    @NotEmpty(message = ("generos não pode estar vazia"))
    private List<String> generos = new ArrayList<>();

    @NotNull(message = ("imagens não pode ser nula"))
    @NotBlank(message = ("imagens não pode estar em branco"))
    private String imagem;

    @NotNull(message = ("albuns não pode ser nula"))
    @NotEmpty(message = ("albuns não pode estar vazia"))
    private List<Album> albuns = new ArrayList<>();

    //Construtor

    public ArtistaDTO(Artista artista) {
        this.id = artista.getId();
        this.nome = artista.getNome();
        this.popularidade = artista.getPopularidade();
        this.seguidores = artista.getSeguidores();
        this.perfil_spotify = artista.getPerfil_spotify();
        this.imagem = artista.getImagem();
        this.generos = artista.getGeneros();
        this.albuns = artista.getAlbuns();
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

    public String getPerfil_spotify() {
        return perfil_spotify;
    }

    public void setPerfil_spotify(String perfil_spotify) {
        this.perfil_spotify = perfil_spotify;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }
}
