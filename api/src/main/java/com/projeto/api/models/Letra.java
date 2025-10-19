package com.projeto.api.models;

import com.projeto.api.util.IdGerador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Letra {
    //Atributos
    @Id
    private String id = IdGerador.Gerar();

    @ManyToOne
    @JoinColumn(name = "id_musica")
    private Musica musica;

    private String letras;

    //Construtor

    public Letra(Musica musica, String letras) {
        this.id = IdGerador.Gerar();
        this.musica = musica;
        this.letras = letras;
    }

    //Geters e Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public String getLetras() {
        return letras;
    }

    public void setLetras(String letras) {
        this.letras = letras;
    }
}
