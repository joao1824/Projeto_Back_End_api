package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.ArtistasDTOs.ArtistaResumoDTO;
import com.projeto.api.dtos.MusicaDTOs.MusicaResumoDTO;
import com.projeto.api.dtos.ReviewDTOs.ReviewResumoDTO;
import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import com.projeto.api.models.Musica;
import com.projeto.api.models.Review;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumDTO toAlbumDTO(Album album);
    Album toAlbum(AlbumDTO albumDTO);
    ArtistaResumoDTO toArtistaDTO(Artista artista);
    MusicaResumoDTO toMusicaDTO(Musica musica);
    ReviewResumoDTO toReviewResumoDTO(Review review);
}
