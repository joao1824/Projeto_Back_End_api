package com.projeto.api.dtos.TagDTOs;

import com.projeto.api.dtos.ReviewDTOs.ReviewDTO;
import com.projeto.api.dtos.ReviewDTOs.ReviewResumoDTO;
import com.projeto.api.models.Tag;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class TagDTO {
    //Atributos


    private String id;


    private String nome;
    private List<ReviewResumoDTO> reviews = new ArrayList<>();

    //Construtor

    public TagDTO(Tag tag) {
        this.id = tag.getId();
        this.nome = tag.getNome();
        this.reviews = tag.getReviews().stream().map(ReviewResumoDTO::new).collect(Collectors.toList());
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

    public List<ReviewResumoDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewResumoDTO> reviews) {
        this.reviews = reviews;
    }
}
