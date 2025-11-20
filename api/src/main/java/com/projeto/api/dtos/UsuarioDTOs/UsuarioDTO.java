package com.projeto.api.dtos.UsuarioDTOs;

import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlayListResumoDTO;
import com.projeto.api.dtos.ReviewDTOs.ReviewDTO;
import com.projeto.api.dtos.ReviewDTOs.ReviewResumoDTO;
import com.projeto.api.models.Usuario;
import com.projeto.api.security.UsuarioRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UsuarioDTO {

    //Atributos

    @Id
    @Size(min = 22, max = 22, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @Column(unique = true)
    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    @Email
    private String email;

    @Size(min = 8,max = 100, message = "senha não é valido pois possui um tamanho não planejado")
    @NotBlank(message = "senha não pode estar em vazio")
    private String senha;

    @NotNull(message = "Role não pode ser nulo")
    private UsuarioRole role;

    private LocalDateTime ultima_atualizacao_senha;
    private LocalDateTime ultima_atualizacao_email;

    private List<ReviewResumoDTO> reviewList = new ArrayList<>();

    private List<PlayListResumoDTO> playLists = new ArrayList<>();


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

    public List<ReviewResumoDTO> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewResumoDTO> reviewList) {
        this.reviewList = reviewList;
    }

    public List<PlayListResumoDTO> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlayListResumoDTO> playLists) {
        this.playLists = playLists;
    }

    public LocalDateTime getUltima_atualizacao_senha() {
        return ultima_atualizacao_senha;
    }

    public void setUltima_atualizacao_senha(LocalDateTime ultima_atualizacao_senha) {
        this.ultima_atualizacao_senha = ultima_atualizacao_senha;
    }

    public LocalDateTime getUltima_atualizacao_email() {
        return ultima_atualizacao_email;
    }

    public void setUltima_atualizacao_email(LocalDateTime ultima_atualizacao_email) {
        this.ultima_atualizacao_email = ultima_atualizacao_email;
    }
}
