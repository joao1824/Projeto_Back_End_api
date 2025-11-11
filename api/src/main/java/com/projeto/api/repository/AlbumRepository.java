package com.projeto.api.repository;

import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, String> {

    Optional<Album> findByNome(String nome);
    @Query("SELECT a FROM Album a JOIN a.artistas ar WHERE a.nome = :nome AND ar.nome = :artistaNome")
    Optional<Album> findByNomeAndArtistas_Nome(@Param("nome") String nome, @Param("artistaNome") String artistaNome);

}
