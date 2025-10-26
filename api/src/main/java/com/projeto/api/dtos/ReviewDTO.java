package com.projeto.api.dtos;

import com.projeto.api.models.Album;
import com.projeto.api.models.Review;
import com.projeto.api.models.Tag;
import com.projeto.api.models.Usuario;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReviewDTO {

    //Atributos

    private String id;
    private String avaliacao;
    private int nota;
    private Tag tag;
    private Album album;
    private Usuario usuario;

    //Construtor

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.avaliacao = review.getAvaliacao();
        this.nota = review.getNota();
        this.tag = review.getTag();
        this.album = review.getAlbum();
        this.usuario = review.getUsuario();
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
