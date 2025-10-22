package com.projeto.api.dtos;

import com.projeto.api.models.Album;
import com.projeto.api.models.Review;
import com.projeto.api.models.Tag;
import com.projeto.api.models.Usuario;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReviewDTO {

    //Atributos
    @Size(min = 27, max = 27, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 1000,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String avaliacao;

    @Max(value = 100, message = "ID não é valido pois possui um tamanho não planejado")
    @Min(value = 0, message = "ID não é valido pois possui um tamanho não planejado")
    @NotBlank(message = "ID não pode estar em vazio")
    @NotNull(message = "O id não pode ser nulo")
    private int nota;

    @NotNull(message = "O tag não pode ser nulo")
    private Tag tag;

    @NotNull(message = "O album não pode ser nulo")
    private Album album;

    @NotNull(message = "O usuario não pode ser nulo")
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
