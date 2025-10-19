package com.projeto.api.dtos;

import com.projeto.api.models.Letra;
import com.projeto.api.models.Musica;
import com.projeto.api.util.IdGerador;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LetraDTO {

    //Atributos
    private String id;
    private String letras;
    private Musica musica;

    //Construtor

    public LetraDTO(Letra letra) {
        this.id = IdGerador.Gerar();
        this.letras = letra.getLetras();
        this.musica = letra.getMusica();
    }

    //Geters e Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLetras() {
        return letras;
    }

    public void setLetras(String letras) {
        this.letras = letras;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }
}
