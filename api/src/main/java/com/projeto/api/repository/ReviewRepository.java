package com.projeto.api.repository;

import com.projeto.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReviewRepository extends JpaRepository<Review, String> {

    long countByDataAfter(LocalDateTime data);

    boolean existsByAlbumIdAndUsuarioId(String id, String id1);
}
