package com.projeto.api.dtos;

import com.projeto.api.models.PlayList;
import com.projeto.api.models.Review;
import com.projeto.api.models.Usuario;
import com.projeto.api.util.IdGerador;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UsuarioDTO {

    //Atributos
    private String id;
    private String nome;
    private String email;
    private List<Review> reviewList = new ArrayList<>();
    private List<PlayList> PlayLists = new ArrayList<>();

    //construtor

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.reviewList = usuario.getReviewList();
        this.PlayLists = usuario.getPlayLists();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<PlayList> getPlayLists() {
        return PlayLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        PlayLists = playLists;
    }
}
