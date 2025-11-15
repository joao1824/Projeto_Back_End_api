package com.projeto.api.service;

//aqui tem problema de divisão por zero quando deleta a ultima review de um album mas quando fizer a interface da pra evitar isso via tar tudo resolvido

import com.projeto.api.dtos.ReviewDTOs.ReviewDTO;
import com.projeto.api.exception.exceptions.EventIdNotFoundException;
import com.projeto.api.models.Album;
import com.projeto.api.models.Review;
import com.projeto.api.models.Tag;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.AlbumRepository;
import com.projeto.api.repository.ReviewRepository;
import com.projeto.api.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Review review = reviewRepository.findById(id).orElseThrow(EventIdNotFoundException::new);
        return new ReviewDTO(review);
    }

    //Nova review (Alterar nota ainda não esta funcionado)
    public ReviewDTO novaReview(ReviewDTO reviewDTO) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();
        Review review = new Review();

        Album album = albumRepository.getReferenceById(reviewDTO.getAlbum().getId());
        Tag tag = tagRepository.getReferenceById(reviewDTO.getTag().getId());

        for (Review r : album.getReviews()) {
            if (r.getUsuario().getId().equals(usuarioLogado.getId())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuário já fez uma review para esse álbum.");
            }
        }



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
        review.setAlbum(album);
        review.setUsuario(usuarioLogado);
        review.setData(LocalDateTime.now());
        review.setTag(tag);
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
        Tag tag = tagRepository.getReferenceById(reviewDTO.getTag().getId());

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

        review.setTag(tag);
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
        Review review = reviewRepository.findById(id).orElseThrow(EventIdNotFoundException::new);
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

    @Autowired
    private ModelMapper modelMapper;

    public List<ReviewDTO> listarTodos() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(r -> modelMapper.map(r, ReviewDTO.class))
                .toList();
    }

    public Map<String, Object> gerarRelatorioPorPeriodo(int dias) {
        LocalDateTime dataLimite = LocalDateTime.now().minusDays(dias);

        long totalNoPeriodo = reviewRepository.countByDataAfter(dataLimite);
        long totalGeral = reviewRepository.count();

        Map<String, Object> relatorio = new HashMap<>();
        relatorio.put("totalNoPeriodo", totalNoPeriodo);
        relatorio.put("totalGeral", totalGeral);

        return relatorio;
    }












}
