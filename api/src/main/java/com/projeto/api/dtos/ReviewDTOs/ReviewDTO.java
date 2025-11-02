package com.projeto.api.dtos.ReviewDTOs;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.dtos.TagDTOs.TagDTO;
import com.projeto.api.dtos.TagDTOs.TagResumoDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioResumoDTO;
import com.projeto.api.models.Review;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class ReviewDTO {

    //Atributos

    private String id;
    private String avaliacao;
    private int nota;
    private LocalDateTime data;
    private TagResumoDTO tag;
    private AlbumResumoDTO album;
    private UsuarioResumoDTO usuario;


    //Construtor


    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.avaliacao = review.getAvaliacao();
        this.nota = review.getNota();
        this.data = review.getData();
        this.tag = new TagResumoDTO(review.getTag());
        this.album = new AlbumResumoDTO(review.getAlbum());
        this.usuario = new UsuarioResumoDTO(review.getUsuario());
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


    public LocalDateTime getData() { return data; }

    public void setData(LocalDateTime data) { this.data = data; }

    public TagResumoDTO getTag() {
        return tag;
    }

    public void setTag(TagResumoDTO tag) {
        this.tag = tag;
    }

    public AlbumResumoDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumResumoDTO album) {
        this.album = album;
    }

    public UsuarioResumoDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResumoDTO usuario) {
        this.usuario = usuario;
    }
}
