package com.projeto.api.dtos.ReviewDTOs;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.TagDTOs.TagDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioDTO;
import com.projeto.api.models.Review;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReviewResumoDTO {
    //Atributos
    private String id;
    private String avaliacao;
    private Integer nota;
    private TagDTO tag;


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

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public TagDTO getTag() {
        return tag;
    }

    public void setTag(TagDTO tag) {
        this.tag = tag;
    }

}
