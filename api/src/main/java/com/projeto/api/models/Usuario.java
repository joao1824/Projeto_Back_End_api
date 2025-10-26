package com.projeto.api.models;


import com.projeto.api.security.UsuarioRole;
import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {

    //Atributos

    @Id
    @Size(min = 22, max = 22, message = "ID deve possuir exatamente 27 caracteres")
    @NotBlank(message = "ID não pode estar vazio")
    private String id;

    @Column(unique = true)
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

    @OneToMany(mappedBy = "usuario")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<PlayList> PlayLists = new ArrayList<>();

    //construtor

    public Usuario( String nome, String email,String senha,UsuarioRole role, List<Review> reviewList, List<PlayList> playLists) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.reviewList = reviewList;
        this.ultima_atualizacao_senha = LocalDateTime.now();
        this.ultima_atualizacao_email = LocalDateTime.now();
        PlayLists = playLists;
    }

    public Usuario(String nome, String email, String encrypedPassoword, UsuarioRole role) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.email = email;
        this.senha = encrypedPassoword;
        this.ultima_atualizacao_senha = LocalDateTime.now();
        this.ultima_atualizacao_email = LocalDateTime.now();
        this.role = role;
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
        return PlayLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        PlayLists = playLists;
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

    public LocalDateTime getUltima_atualizacao_email() {
        return ultima_atualizacao_email;
    }

    public void setUltima_atualizacao_email(LocalDateTime ultima_atualizacao_email) {
        this.ultima_atualizacao_email = ultima_atualizacao_email;
    }

    //Metodos do security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));

    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;    }

    public LocalDateTime getUltima_atualizacao_senha() {
        return ultima_atualizacao_senha;
    }

    public void setUltima_atualizacao_senha(LocalDateTime ultima_atualizacao_senha) {
        this.ultima_atualizacao_senha = ultima_atualizacao_senha;
    }

    public boolean getIsAdmin() {
        return this.role == UsuarioRole.ADMIN;
    }
}
