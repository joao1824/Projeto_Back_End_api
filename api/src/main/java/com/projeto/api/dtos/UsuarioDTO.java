package com.projeto.api.dtos;

import com.projeto.api.models.PlayList;
import com.projeto.api.models.Review;
import com.projeto.api.models.Usuario;
import com.projeto.api.security.UsuarioRole;
import com.projeto.api.util.IdGerador;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UsuarioDTO {

    //Atributos
    @Size(min = 27, max = 27, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @Email(message = "Formato do e-mail invalido")
    @NotBlank(message = "ID não pode estar em vazio")
    private String email;

    @Size(min = 8,max = 100, message = "senha não é valido pois possui um tamanho não planejado")
    @NotBlank(message = "senha não pode estar em vazio")
    private String senha;

    @NotBlank(message = "role não pode estar vazia")
    private UsuarioRole role; //não sei como vai funcionar em dto

    private List<Review> reviewList = new ArrayList<>();

    private List<PlayList> playLists = new ArrayList<>();

    //construtor

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioRole getRole() {
        return role;
    }
    public void setRole(UsuarioRole role) {
        this.role = role;
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
