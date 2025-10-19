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
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<PlayList> PlayLists = new ArrayList<>();

    //construtor


    public Usuario(String nome, String email) {
        this.id = IdGerador.Gerar();
        this.nome = nome;
        this.email = email;
    }
}
