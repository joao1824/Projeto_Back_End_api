package com.projeto.api.models;


import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    private String id;
    private String avaliacao;
    private int nota;

    @ManyToOne
    @JoinColumn(name = "id_tag")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

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
