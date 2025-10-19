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
}
