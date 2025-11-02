package com.projeto.api.dtos.PlaylistDTOs;

import com.projeto.api.dtos.MusicaDTOs.MusicaDTO;
import com.projeto.api.dtos.MusicaDTOs.MusicaResumoDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioResumoDTO;
import com.projeto.api.models.PlayList;
import com.projeto.api.models.Usuario;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class PlayListDTO {

    //Atributos
    private String id;
    private String nome;
    private String descricao;
    private UsuarioResumoDTO usuario;
    private List<MusicaResumoDTO> musicas = new ArrayList<>();

    //Construtor

    public PlayListDTO(PlayList playList) {
        this.id = playList.getId();
        this.nome = playList.getNome();
        this.descricao = playList.getDescricao();
        this.usuario = new UsuarioResumoDTO(playList.getUsuario());
        this.musicas = playList.getMusicas().stream().map(MusicaResumoDTO::new).collect(Collectors.toList());
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

    public UsuarioResumoDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResumoDTO usuario) {
        this.usuario = usuario;
    }

    public List<MusicaResumoDTO> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicaResumoDTO> musicas) {
        this.musicas = musicas;
    }

}
