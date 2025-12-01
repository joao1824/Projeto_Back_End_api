package com.projeto.api.specification;

import java.util.Set;

public enum CamposValidos {

    ALBUM(Set.of(

            "id", "nome", "total_faixas", "lancamento", "gravadora",
            "perfil_spotify", "popularidade", "nota_media", "totalNotas","qtdReviews",
            "imagens", "generos",

            "artistas.id", "artistas.nome", "artistas.popularidade", "artistas.seguidores",
            "artistas.perfil_spotify", "artistas.generos", "artistas.imagem",

            "musicas.id", "musicas.nome", "musicas.duracao", "musicas.explicito",
            "musicas.faixa_numero", "musicas.perfil_spotify",

            "reviews.id", "reviews.avaliacao", "reviews.nota"

    )),
    USUARIO(Set.of(

            "id", "nome", "email","ultima_atualizacao_senha", "ultima_atualizacao_email", "role",
            "reviewList.id", "reviewList.nota", "reviewList.avaliacao",
            "PlayLists.id", "PlayLists.nome", "PlayLists.descricao"

    )),
    TAG(Set.of(

            "id", "nome",
            "reviews.id", "reviews.avaliacao", "reviews.nota"

    )),
    REVIEW(Set.of(
            "id", "avaliacao", "nota", "data",
            "tag.id", "tag.nome",
            "album.id", "album.nome", "album.total_faixas", "album.lancamento",
            "album.gravadora", "album.perfil_spotify", "album.popularidade",
            "album.nota_media", "album.imagens", "album.generos", "album.totalNotas","album.qtdReviews",
            "usuario.id", "usuario.nome", "usuario.email"

    )),
    PLAYLIST(Set.of(

            "id", "nome", "descricao",
            "usuario.id", "usuario.nome", "usuario.email",
            "musicas.id", "musicas.nome", "musicas.duracao", "musicas.explicito",
            "musicas.faixa_numero", "musicas.perfil_spotify"

    )),
    MUSICA(Set.of(

            "id", "nome", "duracao", "explicito", "faixa_numero", "perfil_spotify",
            "album.id", "album.nome", "album.total_faixas", "album.lancamento",
            "album.gravadora", "album.perfil_spotify", "album.popularidade",
            "album.nota_media", "album.imagens", "album.generos", "album.totalNotas","album.qtdReviews",
            "playLists.id", "playLists.nome", "playLists.descricao"

    )),
    ARTISTA(Set.of(

            "id", "nome", "popularidade", "seguidores",
            "perfil_spotify", "generos", "imagem",
            "albuns.id", "albuns.nome", "albuns.total_faixas", "albuns.lancamento",
            "albuns.gravadora", "albuns.perfil_spotify", "albuns.popularidade",
            "albuns.nota_media", "albuns.imagens", "albuns.generos","albuns.totalNotas","albuns.qtdReviews"

    ));

    private final Set<String> campos;

    CamposValidos(Set<String> campos) {
        this.campos = campos;
    }

    public Set<String> getCampos() {
        return campos;
    }
}