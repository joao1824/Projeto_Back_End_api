package com.projeto.api.service;

//aqui tem problema de divisão por zero quando deleta a ultima review de um album mas quando fizer a interface da pra evitar isso via tar tudo resolvido

import com.projeto.api.controller.ReviewController;
import com.projeto.api.dtos.ReviewDTO;
import com.projeto.api.models.Album;
import com.projeto.api.models.Review;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.AlbumRepository;
import com.projeto.api.repository.ReviewRepository;
import com.projeto.api.repository.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    // Injeção do controlador de reviews

    private final TagRepository tagRepository;
    private final AlbumRepository albumRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService( TagRepository tagRepository, AlbumRepository albumRepository, ReviewRepository reviewRepository) {
        this.tagRepository = tagRepository;
        this.albumRepository = albumRepository;
        this.reviewRepository = reviewRepository;
    }

    // Retorna todas as reviews com paginação e ordenação
    public Page<ReviewDTO> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(ReviewDTO::new);
    }

    // Retorna uma review por ID
    public ReviewDTO  getReviewById(String id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma review encontrada."));
        return new ReviewDTO(review);
    }

    //Nova review (Alterar nota ainda não esta funcionado)
    public ReviewDTO novaReview(ReviewDTO reviewDTO) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();
        Review review = new Review();



        Album album = albumRepository.getReferenceById(reviewDTO.getAlbum().getId());
        //calculo da nova nota media

        List<Integer> notasExistentes = album.getReviews().stream()
                .map(Review::getNota)
                .toList();
        //soma das notas existentes
        int soma = notasExistentes.stream()
                .mapToInt(Integer::intValue)
                .sum();
        //calcula a nova nota media
        float novaNotaMedia = ((float) soma + reviewDTO.getNota()) / (notasExistentes.size() + 1);

        album.setNota_media(novaNotaMedia);
        albumRepository.save(album);

        review.setAvaliacao(reviewDTO.getAvaliacao());
        review.setNota(reviewDTO.getNota());
        review.setAlbum(albumRepository.getReferenceById(reviewDTO.getAlbum().getId()));
        review.setUsuario(usuarioLogado);
        review.setTag(tagRepository.getReferenceById(reviewDTO.getTag().getId()));
        review.setData(LocalDateTime.now());
        reviewRepository.save(review);
        return new ReviewDTO(review);
    }

    //Atualiza review
    public ReviewDTO atualizarReview(String id, ReviewDTO reviewDTO) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();
        Review review = reviewRepository.getReferenceById(id);
        Album album = albumRepository.getReferenceById(review.getAlbum().getId());

        //Ve se é o mesmo id do usuário da review
        if (!usuarioLogado.getId().equals(review.getUsuario().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuário não é dono da Review.");
        }

        List<Integer> notasExistentes = album.getReviews().stream()
                .map(Review::getNota)
                .toList();
        //remove a nota antiga
        int soma = (notasExistentes.stream()
                .mapToInt(Integer::intValue)
                .sum()) - review.getNota();
        //calcula a nova nota media
        float novaNotaMedia = ((float) soma + reviewDTO.getNota()) / (notasExistentes.size());

        album.setNota_media(novaNotaMedia);
        albumRepository.save(album);

        review.setAvaliacao(reviewDTO.getAvaliacao());
        review.setNota(reviewDTO.getNota());
        reviewRepository.save(review);

        return (new ReviewDTO(review));
    }

    //Deleta review
    public void deletarReview(String id) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma review encontrada."));
        Album album = albumRepository.getReferenceById(review.getAlbum().getId());

        //Ve se é o mesmo id do usuário da review
        if (!usuarioLogado.getId().equals(review.getUsuario().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuário não é dono da Review.");
        }

        List<Integer> notasExistentes = album.getReviews().stream()
                .map(Review::getNota)
                .toList();
        //remove a nota
        int soma = (notasExistentes.stream()
                .mapToInt(Integer::intValue)
                .sum()) - review.getNota();
        //calcula a nova nota media
        float novaNotaMedia = ((float) soma) / (notasExistentes.size() - 1);

        album.setNota_media(novaNotaMedia);
        albumRepository.save(album);

        reviewRepository.delete(review);
    }










}
