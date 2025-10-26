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
public class Musica {


    //Atributos

    @Id
    @Size(min = 22, max = 22, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @NotBlank(message = "duracao não pode estar em branco")
    @Positive(message = "duracao não pode ser negativa")
    private int duracao;

    @NotBlank(message = "explicito não pode estar em branco")
    private boolean explicito; //se é +18 ou não

    @NotBlank(message = "faixa_numero não pode estar em branco")
    @Positive(message = "faixa_numero não pode ser negativa")
    private int faixa_numero;

    @NotBlank(message = "perfil_spotifynão pode estar vazio")
    private String perfil_spotify;
    private String letra;

    @NotBlank(message = "album não pode estar vazio")
    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

    @NotEmpty(message = ("playLists não pode estar vazia"))
    @ManyToMany(mappedBy = "musicas")
    private List<PlayList> playLists = new ArrayList<>();

    //Construtor



    public Musica(String nome, int duracao, boolean explicito, int faixa_numero, String perfil_spotify, Album album, List<PlayList> playLists) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.duracao = duracao;
        this.explicito = explicito;
        this.faixa_numero = faixa_numero;
        this.perfil_spotify = perfil_spotify;
        this.album = album;
        this.playLists = playLists;
    }


    public Musica(String nome, int duracao, boolean explicito, int faixa_numero, String perfil_spotify, Album album) {
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

    public List<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
    }
}
