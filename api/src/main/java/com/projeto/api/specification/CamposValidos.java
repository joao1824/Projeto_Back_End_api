package com.projeto.api.specification;

import java.util.Set;

public enum CamposValidos {

    ALBUM(Set.of(
            "id", "nome", "total_faixas", "lancamento",
            "gravadora", "perfil_spotify", "popularidade",
            "nota_media", "imagens", "generos"
    )),
    USUARIO(Set.of(
            "id", "nome", "email"
    )),
    TAG(Set.of(
            "id", "nome"
    )),
    REVIEW(Set.of(
            "id", "avaliacao", "nota",
            "tag.id", "tag.nome",
            "album.id", "album.nome",
            "usuario.id", "usuario.nome", "usuario.email"
    )),
    PLAYLIST(Set.of(
            "id", "nome", "descricao",
            "usuario.id", "usuario.nome", "usuario.email"
    )),
    MUSICA(Set.of(
            "id", "nome", "duracao", "explicito",
            "faixa_numero", "perfil_spotify",
            "album.id", "album.nome"
    )),
    ARTISTA(Set.of(
            "id", "nome", "popularidade", "seguidores",
            "perfil_spotify", "generos", "imagem"
    ));

    private final Set<String> campos;

    CamposValidos(Set<String> campos) {
        this.campos = campos;
    }

    public Set<String> getCampos() {
        return campos;
    }
}