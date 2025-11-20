package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.ReviewDTOs.ReviewDTO;
import com.projeto.api.models.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO toReviewDTO(Review review);
}
