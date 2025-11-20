package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.models.PlayList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayListMapper {
    PlayListDTO toPlayListDTO(PlayList playlist);
}
