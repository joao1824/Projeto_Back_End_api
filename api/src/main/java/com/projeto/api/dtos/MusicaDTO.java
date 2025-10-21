package com.projeto.api.dtos;

import com.projeto.api.models.Album;
import com.projeto.api.models.Musica;
import com.projeto.api.models.PlayList;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class MusicaDTO {

    //Atributos

    private String id;
    private String nome;
    private int duracao;
    private boolean explicito;
    private int faixa_numero;
    private String perfil_spotify;
    private Album album;
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
