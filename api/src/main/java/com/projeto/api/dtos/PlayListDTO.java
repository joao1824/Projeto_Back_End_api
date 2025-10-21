package com.projeto.api.dtos;

import com.projeto.api.models.Musica;
import com.projeto.api.models.PlayList;
import com.projeto.api.models.Usuario;
import com.projeto.api.util.IdGerador;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class PlayListDTO {

    //Atributos

    @Max(value = 27, message = "ID não é valido pois possui um tamanho não planejado")
    @Min(value = 27, message = "ID não é valido pois possui um tamanho não planejado")
    @NotBlank(message = "ID não pode estar em vazio")
    @NotNull(message = "O id não pode ser nulo")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @Size(min = 0, max = 500,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "O lancamento não pode estar vazio")
    @NotNull(message = "O lancamento não pode ser nulo")
    private String descricao;

    @NotNull(message = ("usuario não pode ser nula"))
    private Usuario usuario;

    @NotNull(message = ("musicas não pode ser nula"))
    @NotEmpty(message = ("musicas não pode estar vazia"))
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
