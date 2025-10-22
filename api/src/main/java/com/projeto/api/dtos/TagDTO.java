package com.projeto.api.dtos;

import com.projeto.api.models.Review;
import com.projeto.api.models.Tag;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TagDTO {
    //Atributos

    @Size(min = 27, max = 27, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @NotNull(message = ("reviews não pode ser nula"))
    @NotEmpty(message = ("reviews não pode estar vazia"))
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
