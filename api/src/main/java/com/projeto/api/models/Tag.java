package com.projeto.api.models;


import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 22, max = 22, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @OneToMany
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
