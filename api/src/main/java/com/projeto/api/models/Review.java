package com.projeto.api.models;


import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    //Atributos
    @Id
    @Size(min = 22, max = 22, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 1000,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String avaliacao;

    @Max(value = 100, message = "ID não é valido pois possui um tamanho não planejado")
    @Min(value = 0, message = "ID não é valido pois possui um tamanho não planejado")
    private int nota;


    @NotNull(message = "O tag não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "id_tag")
    private Tag tag;

    @NotNull(message = "O album não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

    @NotNull(message = "O usuario não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    //Construtor


    public Review(Tag tag, Album album, Usuario usuario, String avaliacao, int nota) {
        this.id = IdGerador.Gerar();
        this.tag = tag;
        this.album = album;
        this.usuario = usuario;
        this.avaliacao = avaliacao;
        this.nota = nota;
    }


    //Geters e Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
