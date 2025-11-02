package com.projeto.api.dtos.PlaylistDTOs;

import com.projeto.api.models.PlayList;
import com.projeto.api.models.Usuario;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlayListResumoDTO {
    //Atributos
    private String id;
    private String nome;
    private String descricao;

    //Construtor
    public PlayListResumoDTO(PlayList playlist) {
        this.id = playlist.getId();
        this.nome = playlist.getNome();
        this.descricao = playlist.getDescricao();
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
}
