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

    @Id
    private String id;

    private String nome;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Review> reviews = new ArrayList<>();

    public Tag(String nome, List<Review> reviews) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.reviews = reviews;
    }
}
