package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.dtos.ArtistasDTOs.ArtistaDTO;
import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistaMapper {
    ArtistaDTO toDto(Artista artista);
    AlbumResumoDTO toDto(Album album);
}
