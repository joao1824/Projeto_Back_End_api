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
public class Tag {

    //Atributos

    @Id
    private String id;

    private String nome;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Review> reviews = new ArrayList<>();



    //construtor

    public Tag(String nome) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
    }


    public Tag(String nome, List<Review> reviews) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.reviews = reviews;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
