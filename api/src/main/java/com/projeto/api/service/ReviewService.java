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
import com.projeto.api.specification.CamposValidos;
import com.projeto.api.specification.ReviewSpecification;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Page<ReviewDTO> getAllReviews(Pageable pageable , Map<String, String> filtros) {

        Set<String> camposValidos = CamposValidos.REVIEW.getCampos();

        Map<String, String> filtrosValidos = filtros.entrySet().stream()
                .filter(entry -> camposValidos.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Specification<Review> specification = ReviewSpecification.aplicarFiltros(filtrosValidos);

        Page<Review> reviews = reviewRepository.findAll(specification,pageable);
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum review encontrado");
        }
        return reviews.map(reviewMapper::toReviewDTO);
    }

    // Retorna uma review por ID
    public ReviewDTO  getReviewById(String id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review não encontrada com o ID: " + id));
        return reviewMapper.toReviewDTO(review);
    }

    //Nova review (Alterar nota ainda não esta funcionado)
    @Transactional
    public ReviewDTO newReview(ReviewDTO reviewDTO) {

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
        Tag tag = tagRepository.findById(reviewDTO.getTag().getId()).orElseThrow(() -> new TagNotFoundException("Tag não encontrada."));
        Album album = albumRepository.findById(reviewDTO.getAlbum().getId()).orElseThrow(() -> new AlbumNotFoundException("Álbum não encontrado."));
        boolean existe = reviewRepository.existsByAlbumIdAndUsuarioId(album.getId(), usuarioLogado.getId());

        if (existe) {
            throw new ReviewExistenteException("Review já existente para este usuário e álbum.");
        }

        //calculo da nova nota media

        albumRepository.incrementarNota(album.getId(), reviewDTO.getNota());
        Album album_atulizado = albumRepository.findById(reviewDTO.getAlbum().getId()).orElseThrow(() -> new AlbumNotFoundException("Álbum não encontrado."));

        review.setAvaliacao(reviewDTO.getAvaliacao());
        review.setNota(reviewDTO.getNota());
        review.setUsuario(usuarioLogado);
        review.setData(LocalDateTime.now());
        review.setTag(tag);
        review.setAlbum(album_atulizado);
        reviewRepository.save(review);
        return reviewMapper.toReviewDTO(review);
    }

    //Atualiza review
    @Transactional
    public ReviewDTO updateReview(String id, ReviewDTO reviewDTO) {
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

        //atualiza a nota media
        albumRepository.atualizarNota(album.getId(), review.getNota(), reviewDTO.getNota());

        review.setTag(tag);
        review.setAvaliacao(reviewDTO.getAvaliacao());
        review.setNota(reviewDTO.getNota());

        reviewRepository.save(review);

        return reviewMapper.toReviewDTO(review);
    }

    //Deleta review
    @Transactional
    public void deleteReview(String id) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review não encontrada com o ID: " + id));
        Album album = review.getAlbum();

        //Ve se é o mesmo id do usuário da review
        if (!usuarioLogado.getId().equals(review.getUsuario().getId())) {
            throw new OwnerUserException();
        }

        albumRepository.decrementarNota(album.getId(), review.getNota());
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
