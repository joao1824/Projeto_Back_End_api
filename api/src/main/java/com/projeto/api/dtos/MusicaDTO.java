package com.projeto.api.dtos;

import com.projeto.api.models.Album;
import com.projeto.api.models.Musica;
import com.projeto.api.models.PlayList;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class MusicaDTO {

    //Atributos

    @Size(max = 27, min = 27, message = "ID não é valido pois possui um tamanho não planejado")
    @NotBlank(message = "ID não pode estar em vazio")
    @NotNull(message = "O id não pode ser nulo")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "duracao não pode estar em branco")
    @Positive(message = "duracao não pode ser negativa")
    @NotNull(message = "duracao não pode ser nulo")
    private int duracao;

    @NotBlank(message = "explicito não pode estar em branco")
    @NotNull(message = "explicito não pode ser nulo")
    private boolean explicito;

    @NotBlank(message = "faixa_numero não pode estar em branco")
    @Positive(message = "faixa_numero não pode ser negativa")
    @NotNull(message = "faixa_numero não pode ser nulo")
    private int faixa_numero;

    @NotBlank(message = "perfil_spotifynão pode estar vazio")
    @NotNull(message = "perfil_spotify não pode ser nulo")
    private String perfil_spotify;

    @NotNull(message = ("album não pode ser nula"))
    @NotBlank(message = "album não pode estar vazio")
    private Album album;

    @NotNull(message = ("playLists não pode ser nula"))
    @NotEmpty(message = ("playLists não pode estar vazia"))
    private List<PlayList> playLists = new ArrayList<>();

    //Construtor

    public MusicaDTO(Musica musica) {
        this.id = musica.getId();
        this.nome = musica.getNome();
        this.duracao = musica.getDuracao();
        this.explicito = musica.isExplicito();
        this.faixa_numero = musica.getFaixa_numero();
        this.perfil_spotify = musica.getPerfil_spotify();
        this.album = musica.getAlbum();
        this.playLists = musica.getPlayLists();
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
    }
}
