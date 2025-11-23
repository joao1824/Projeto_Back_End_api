package com.projeto.api.repository;

import com.projeto.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;

public interface ReviewRepository extends JpaRepository<Review, String>, JpaSpecificationExecutor<Review> {

    long countByDataAfter(LocalDateTime data);

    boolean existsByAlbumIdAndUsuarioId(String id, String id1);
}
