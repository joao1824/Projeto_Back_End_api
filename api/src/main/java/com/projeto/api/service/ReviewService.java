package com.projeto.api.service;

//aqui tem problema de divisão por zero quando deleta a ultima review de um album mas quando fizer a interface da pra evitar isso via tar tudo resolvido

import com.projeto.api.dtos.ReviewDTOs.ReviewDTO;
import com.projeto.api.exception.exceptions.*;
import com.projeto.api.mapper.dtos.ReviewMapper;
import com.projeto.api.models.Album;
import com.projeto.api.models.Review;
import com.projeto.api.models.Tag;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.AlbumRepository;
import com.projeto.api.repository.ReviewRepository;
import com.projeto.api.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    private final ReviewMapper reviewMapper;

    public ReviewService(TagRepository tagRepository, AlbumRepository albumRepository, ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.tagRepository = tagRepository;
        this.albumRepository = albumRepository;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    // Retorna todas as reviews com paginação e ordenação
    public Page<ReviewDTO> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(reviewMapper::toReviewDTO);
    }

    // Retorna uma review por ID
    public ReviewDTO  getReviewById(String id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review não encontrada com o ID: " + id));
        return reviewMapper.toReviewDTO(review);
    }

    //Nova review (Alterar nota ainda não esta funcionado)
    public ReviewDTO novaReview(ReviewDTO reviewDTO) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();
        Review review = new Review();

        //validações
        if (reviewDTO.getAlbum() == null || reviewDTO.getAlbum().getId() == null)
            throw new EmptyException("O ID do álbum deve ser informado.");

        if (reviewDTO.getTag() == null || reviewDTO.getTag().getId() == null)
            throw new EmptyException("O ID da tag deve ser informado.");

        if (reviewDTO.getNota() == null)
            throw new EmptyException("A nota é obrigatória.");

        //verifica se o album existe

        Album album = albumRepository.findById(reviewDTO.getAlbum().getId()).orElseThrow(() -> new AlbumNotFoundException("Álbum não encontrado."));

        Tag tag = tagRepository.findById(reviewDTO.getTag().getId()).orElseThrow(() -> new TagNotFoundException("Tag não encontrada."));

        boolean existe = reviewRepository.existsByAlbumIdAndUsuarioId(album.getId(), usuarioLogado.getId());

        if (existe) {
            throw new ReviewExistenteException("Review já existente para este usuário e álbum.");
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
        return reviewMapper.toReviewDTO(review);
    }

    //Atualiza review
    public ReviewDTO atualizarReview(String id, ReviewDTO reviewDTO) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        //validações
        if (reviewDTO.getTag() == null || reviewDTO.getTag().getId() == null)
            throw new EmptyException("O ID da tag deve ser informado.");

        if (reviewDTO.getNota() == null)
            throw new EmptyException("A nota é obrigatória.");

        //busca review, album e tag
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review não encontrada com o ID: " + id));
        Album album = review.getAlbum();
        Tag tag = tagRepository.findById(reviewDTO.getTag().getId()).orElseThrow(() -> new TagNotFoundException("Tag não encontrada."));

        //Ve se é o mesmo id do usuário da review
        if (!usuarioLogado.getId().equals(review.getUsuario().getId())) {
            throw new OwnerUserException();
        }

        //calculo da nova nota media
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

        return reviewMapper.toReviewDTO(review);
    }

    //Deleta review
    public void deletarReview(String id) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review não encontrada com o ID: " + id));
        Album album = review.getAlbum();

        //Ve se é o mesmo id do usuário da review
        if (!usuarioLogado.getId().equals(review.getUsuario().getId())) {
            throw new OwnerUserException();
        }

        List<Integer> notasExistentes = album.getReviews().stream()
                .map(Review::getNota)
                .toList();
        //remove a nota
        int soma = (notasExistentes.stream()
                .mapToInt(Integer::intValue)
                .sum()) - review.getNota();
        //calcula a nova nota media
        if (notasExistentes.size() == 1){
            album.setNota_media(null);
            albumRepository.save(album);
            reviewRepository.delete(review);
            return;
        }else {
            float novaNotaMedia = ((float) soma) / (notasExistentes.size() - 1);
            album.setNota_media(novaNotaMedia);
            albumRepository.save(album);
            reviewRepository.delete(review);
        }

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
