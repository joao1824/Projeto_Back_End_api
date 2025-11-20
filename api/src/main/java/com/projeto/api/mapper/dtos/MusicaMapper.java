package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.dtos.MusicaDTOs.MusicaDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlayListResumoDTO;
import com.projeto.api.models.Album;
import com.projeto.api.models.Musica;
import com.projeto.api.models.PlayList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicaMapper {
    MusicaDTO toDto(Musica musica);
    AlbumResumoDTO toDto(Album album);
    PlayListResumoDTO toDto(PlayList playlist);
}
