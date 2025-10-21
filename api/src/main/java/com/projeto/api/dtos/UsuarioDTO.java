package com.projeto.api.dtos;

import com.projeto.api.models.PlayList;
import com.projeto.api.models.Review;
import com.projeto.api.models.Usuario;
import com.projeto.api.util.IdGerador;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UsuarioDTO {

    //Atributos
    @Max(value = 27, message = "ID não é valido pois possui um tamanho não planejado")
    @Min(value = 27, message = "ID não é valido pois possui um tamanho não planejado")
    @NotBlank(message = "ID não pode estar em vazio")
    @NotNull(message = "O id não pode ser nulo")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @Email
    @NotBlank(message = "ID não pode estar em vazio")
    @NotNull(message = "O id não pode ser nulo")
    private String email;

    @NotNull(message = ("reviews não pode ser nula"))
    @NotEmpty(message = ("reviews não pode estar vazia"))
    private List<Review> reviewList = new ArrayList<>();

    @NotNull(message = ("playLists não pode ser nula"))
    @NotEmpty(message = ("playLists não pode estar vazia"))
    private List<PlayList> playLists = new ArrayList<>();

    //construtor

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.reviewList = usuario.getReviewList();
        this.playLists = usuario.getPlayLists();
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
        return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
    }
}
