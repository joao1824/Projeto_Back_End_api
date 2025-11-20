package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.MusicaDTOs.MusicaDTO;
import com.projeto.api.models.Musica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicaMapper {

    MusicaDTO toDto(Musica musica);

}
