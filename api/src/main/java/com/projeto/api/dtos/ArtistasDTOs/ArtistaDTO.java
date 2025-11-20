package com.projeto.api.dtos.ArtistasDTOs;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.models.Artista;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ArtistaDTO {

    //Atributos
    @Id
    @Size(min = 22, max = 22, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    @Column(unique = true)
    private String nome;

    @Positive(message = "popularidade não pode ser negativa")
    private int popularidade;

    @Positive(message = "seguidores não pode ser negativa")
    private int seguidores;

    @NotBlank(message = "O perfil não pode estar vazio")
    public String perfil_spotify;

    @ElementCollection
    private List<String> generos = new ArrayList<>();

    private String imagem;

    private List<AlbumResumoDTO> albuns = new ArrayList<>();


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

    public List<AlbumResumoDTO> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<AlbumResumoDTO> albuns) {
        this.albuns = albuns;
    }
}
