package com.projeto.api.repository;

import com.projeto.api.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, String> {
}
