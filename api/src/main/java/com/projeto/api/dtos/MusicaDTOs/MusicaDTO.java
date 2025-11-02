package com.projeto.api.dtos.MusicaDTOs;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlayListResumoDTO;
import com.projeto.api.models.Musica;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class MusicaDTO {

    //Atributos

    private String id;
    private String nome;
    private int duracao;
    private boolean explicito;
    private int faixa_numero;
    private String perfil_spotify;
    private AlbumResumoDTO album;
    private List<PlayListResumoDTO> playLists = new ArrayList<>();

    //Construtor

    public MusicaDTO(Musica musica) {
        this.id = musica.getId();
        this.nome = musica.getNome();
        this.duracao = musica.getDuracao();
        this.explicito = musica.isExplicito();
        this.faixa_numero = musica.getFaixa_numero();
        this.perfil_spotify = musica.getPerfil_spotify();
        this.album = new AlbumResumoDTO(musica.getAlbum());
        this.playLists = musica.getPlayLists().stream().map(PlayListResumoDTO::new).collect(Collectors.toList());
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
