package com.projeto.api.models;


import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Musica {


    //Atributos

    @Id
    private String id;
    private String nome;
    private int duracao;
    private boolean explicito; //se é +18 ou não
    private int faixa_numero;
    private String perfil_spotify;
    private String letra;

    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    @ManyToMany(mappedBy = "musicas")
    private List<PlayList> playLists = new ArrayList<>();

    //Construtor


<<<<<<< Updated upstream
    public Musica(String nome, int duracao, boolean explicito, int faixa_numero, String perfil_spotify, Album album, List<PlayList> playLists) {
=======
    public Musica(String nome, int duracao, boolean explicito, int faixa_numero, String perfil_spotify, Album album, String letra, List<PlayList> playLists) {
>>>>>>> Stashed changes
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.duracao = duracao;
        this.explicito = explicito;
        this.faixa_numero = faixa_numero;
        this.perfil_spotify = perfil_spotify;
        this.album = album;

        this.playLists = playLists;
    }

<<<<<<< Updated upstream
    public Musica(String nome, int duracao, boolean explicito, int faixa_numero, String perfil_spotify, Album album) {
=======
    public Musica(String nome, int duracao, boolean explicito, int faixa_numero, String perfil_spotify, Album album, String letra) {
>>>>>>> Stashed changes
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.duracao = duracao;
        this.explicito = explicito;
        this.faixa_numero = faixa_numero;
        this.perfil_spotify = perfil_spotify;
        this.album = album;
        this.letra = letra;
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

<<<<<<< Updated upstream
=======
    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

>>>>>>> Stashed changes
    public List<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
    }
}
