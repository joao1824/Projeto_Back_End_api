package com.projeto.api.models;


import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Entity
public class PlayList {
    //Atribuos
    @Id
    @Size(min = 22, max = 22, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;


    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @Size(min = 0, max = 500,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "O lancamento não pode estar vazio")
    private String descricao;

    @NotNull(message = ("usuario não pode ser nula"))
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @NotNull(message = ("musicas não pode ser nula"))
    @NotEmpty(message = ("musicas não pode estar vazia"))
    @ManyToMany
    @JoinTable(
            name = "playlist_musica",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicas = new ArrayList<>();


    //Construtor

    public PlayList() {
        this.id = IdGerador.Gerar();
    }


    public PlayList(String nome, String descricao, Usuario usuario, List<Musica> musicas) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.descricao = descricao;
        this.usuario = usuario;
        this.musicas = musicas;
    }


    //Método personalizado

    public void addMusica(Musica musica){
        this.musicas.add(musica);
    }

    public void removeMusica(Musica musica){
        this.musicas.remove(musica);
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
