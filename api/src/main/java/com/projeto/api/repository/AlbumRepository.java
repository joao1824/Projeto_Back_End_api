package com.projeto.api.repository;

import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, String>, JpaSpecificationExecutor<Album> {

    Optional<Album> findByNome(String nome);
    @Query("SELECT a FROM Album a JOIN a.artistas ar WHERE a.nome = :nome AND ar.nome = :artistaNome")
    Optional<Album> findByNomeAndArtistas_Nome(@Param("nome") String nome, @Param("artistaNome") String artistaNome);

    @Modifying
    @Transactional
    @Query("UPDATE Album a " +
            "SET a.totalNotas = COALESCE(a.totalNotas, 0) + :novaNota, " +
            "    a.qtdReviews = COALESCE(a.qtdReviews, 0) + 1, " +
            "    a.nota_media = (COALESCE(a.totalNotas, 0) + :novaNota) / (COALESCE(a.qtdReviews, 0) + 1) " +
            "WHERE a.id = :albumId")
    void incrementarNota(@Param("albumId") String albumId, @Param("novaNota") int novaNota);

    @Modifying
    @Transactional
    @Query("UPDATE Album a " +
            "SET a.totalNotas = COALESCE(a.totalNotas, 0) - :notaAntiga + :notaNova, " +
            "    a.nota_media = (COALESCE(a.totalNotas, 0) - :notaAntiga + :notaNova) / COALESCE(a.qtdReviews, 1) " +
            "WHERE a.id = :albumId")
    void atualizarNota(@Param("albumId") String albumId, @Param("notaAntiga") int notaAntiga, @Param("notaNova") int notaNova);

    @Modifying
    @Transactional
    @Query("UPDATE Album a " +
            "SET a.totalNotas = COALESCE(a.totalNotas, 0) - :notaAntiga, " +
            "    a.qtdReviews = COALESCE(a.qtdReviews, 1) - 1, " +
            "    a.nota_media = CASE WHEN COALESCE(a.qtdReviews, 1) - 1 = 0 THEN NULL ELSE (COALESCE(a.totalNotas, 0) - :notaAntiga) / (COALESCE(a.qtdReviews, 1) - 1) END " +
            "WHERE a.id = :albumId")
    void decrementarNota(@Param("albumId") String albumId, @Param("notaAntiga") int notaAntiga);





}
