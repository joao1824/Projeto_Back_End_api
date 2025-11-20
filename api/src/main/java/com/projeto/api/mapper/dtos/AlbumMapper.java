package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.models.Album;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumDTO toAlbumDTO(Album album);
    Album toAlbum(AlbumDTO albumDTO);
}
