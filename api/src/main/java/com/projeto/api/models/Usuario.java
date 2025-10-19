package com.projeto.api.models;


import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;




@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    //Atributos

    @Id
    private String id;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<PlayList> PlayLists = new ArrayList<>();

    //construtor


    public Usuario( String nome, String email, List<Review> reviewList, List<PlayList> playLists) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.email = email;
        this.reviewList = reviewList;
        PlayLists = playLists;
    }

    public Usuario(String nome, String email) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.email = email;
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
