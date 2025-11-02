package com.projeto.api.dtos.TagDTOs;

import com.projeto.api.models.Tag;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TagResumoDTO {

    //Atributos
    private String id;
    private String nome;

    //Construtor
    public TagResumoDTO(Tag tag) {
        this.id = tag.getId();
        this.nome = tag.getNome();
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
}
