package com.projeto.api.dtos;

import com.projeto.api.models.Musica;
import com.projeto.api.models.PlayList;
import com.projeto.api.models.Usuario;
import com.projeto.api.util.IdGerador;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class PlayListDTO {

    //Atributos
    private String id;
    private String nome;
    private String descricao;
    private Usuario usuario;
    private List<Musica> musicas = new ArrayList<>();

    //Construtor

    public PlayListDTO(PlayList playList) {
        this.id = playList.getId();
        this.nome = playList.getNome();
        this.descricao = playList.getDescricao();
        this.usuario = playList.getUsuario();
        this.musicas = playList.getMusicas();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
}
