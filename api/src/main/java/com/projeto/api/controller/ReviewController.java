package com.projeto.api.controller;


import com.projeto.api.dtos.ReviewDTOs.ReviewDTO;
import com.projeto.api.service.ReviewService;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    // Injeção do serviço de reviews

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Retorna todas as reviews com paginação e ordenação

    @GetMapping
    public Page<ReviewDTO> getAllReviews(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return  reviewService.getAllReviews(pageable);
    }

    // Retorna uma review por ID
    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable String id) {
        return reviewService.getReviewById(id);
    }

    // Novo review

    @PostMapping
    public ReviewDTO novaReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.novaReview(reviewDTO);
    }

    // Atualiza uma review existente
    @PutMapping("/{id}")
    public ReviewDTO atualizarReview(@PathVariable String id, @RequestBody ReviewDTO reviewDTO) {
        return reviewService.atualizarReview(id, reviewDTO);
    }

    // Deleta uma review por ID
    @DeleteMapping("/{id}")
    public void deletarReview(@PathVariable String id) {
        reviewService.deletarReview(id);
    }







}
