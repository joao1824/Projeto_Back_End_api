package com.projeto.api.dtos;

import com.projeto.api.models.Review;
import com.projeto.api.models.Tag;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TagDTO {
    //Atributos


    private String id;


    private String nome;
    private List<Review> reviews = new ArrayList<>();

    //Construtor

    public TagDTO(Tag tag) {
        this.id = tag.getId();
        this.nome = tag.getNome();
        this.reviews = tag.getReviews();
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
