package com.projeto.api.dtos.MusicaDTOs;

import com.projeto.api.models.Musica;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MusicaResumoDTO {
    //Atributos
    private String id;
    private String nome;
    private int duracao;
    private boolean explicito;
    private int faixa_numero;
    private String perfil_spotify;

    //Construtor
    public MusicaResumoDTO(Musica musica) {
        this.id = musica.getId();
        this.nome = musica.getNome();
        this.duracao = musica.getDuracao();
        this.explicito = musica.isExplicito();
        this.faixa_numero = musica.getFaixa_numero();
        this.perfil_spotify = musica.getPerfil_spotify();
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
}
