package com.projeto.api.repository;

import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, String> {

    Optional<Album> findByNome(String nome);
}
