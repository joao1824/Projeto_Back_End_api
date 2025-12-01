package com.projeto.api.dtos.ArtistasDTOs;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.models.Artista;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ArtistaDTO {

    //Não é nescessário validar o id pois ele é gerado automaticamente quando passa pelo mapper, atraves da função id gerador
    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    @Column(unique = true)
    private String nome;

    @PositiveOrZero(message = "popularidade não pode ser negativa")
    private Integer popularidade;

    @PositiveOrZero(message = "seguidores não pode ser negativa")
    private Integer seguidores;

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

    public Integer getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(Integer popularidade) {
        this.popularidade = popularidade;
    }

    public Integer getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Integer seguidores) {
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
