package com.projeto.api.dtos.AlbumDTOs;

public record AlbumFilter(  String id,
                            String nome,
                            Integer total_faixas,
                            String lancamento,
                            String gravadora,
                            String perfil_spotify,
                            Integer popularidade,
                            Float nota_media) {
}
