package com.projeto.api.models;


import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    private String id;
    private String avaliacao;
    private int nota;

    @ManyToOne
    @JoinColumn(name = "id_tag")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    //Construtor


    public Review(Tag tag, Album album, Usuario usuario, String avaliacao, int nota) {
        this.id = IdGerador.Gerar();
        this.tag = tag;
        this.album = album;
        this.usuario = usuario;
        this.avaliacao = avaliacao;
        this.nota = nota;
    }
}
