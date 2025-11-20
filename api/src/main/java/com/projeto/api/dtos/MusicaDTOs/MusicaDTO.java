package com.projeto.api.dtos.MusicaDTOs;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlayListResumoDTO;
import com.projeto.api.models.Musica;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class MusicaDTO {

    //Atributos

    @Id
    @Size(min = 22, max = 22, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @NotNull(message = "A duração não pode ser nula")
    @Positive(message = "duracao não pode ser negativa")
    private int duracao;

    private boolean explicito; //se é +18 ou não

    @NotNull(message = "A faixa precisa ter um número")
    @Min(value = 1, message = "O número da faixa deve ser pelo menos 1")
    private int faixa_numero;

    @NotBlank(message = "perfil_spotifynão pode estar vazio")
    private String perfil_spotify;

    @NotNull(message = "O album não pode ser nulo")
    private AlbumResumoDTO album;

    private List<PlayListResumoDTO> playLists = new ArrayList<>();


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

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public boolean isExplicito() {
        return explicito;
    }

    public void setExplicito(boolean explicito) {
        this.explicito = explicito;
    }

    public int getFaixa_numero() {
        return faixa_numero;
    }

    public void setFaixa_numero(int faixa_numero) {
        this.faixa_numero = faixa_numero;
    }

    public String getPerfil_spotify() {
        return perfil_spotify;
    }

    public void setPerfil_spotify(String perfil_spotify) {
        this.perfil_spotify = perfil_spotify;
    }

    public AlbumResumoDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumResumoDTO album) {
        this.album = album;
    }

    public List<PlayListResumoDTO> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlayListResumoDTO> playLists) {
        this.playLists = playLists;
    }
}
