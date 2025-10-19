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

    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

    @OneToMany(mappedBy = "musica")
    private List<Letra> letra = new ArrayList<>();

    @ManyToMany(mappedBy = "musicas")
    private List<PlayList> playLists = new ArrayList<>();

    //Construtor

    public Musica(String nome, int duracao, boolean explicito, int faixa_numero, String perfil_spotify, Album album, Letra letra) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.duracao = duracao;
        this.explicito = explicito;
        this.faixa_numero = faixa_numero;
        this.perfil_spotify = perfil_spotify;
        this.album = album;
    }


    //Geters e Setters



}
