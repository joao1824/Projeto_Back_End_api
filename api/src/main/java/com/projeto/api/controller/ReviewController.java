package com.projeto.api.controller;


import com.projeto.api.dtos.ReviewDTOs.ReviewDTO;
import com.projeto.api.service.ReviewService;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

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
    public Page<ReviewDTO> getAllReviews(@RequestParam Map<String,String> filtros,@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return  reviewService.getAllReviews(filtros,pageable);
    }

    // Retorna uma review por ID
    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable String id) {
        return reviewService.getReviewById(id);
    }

    // Novo review

    @PostMapping
    public ReviewDTO newReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.newReview(reviewDTO);
    }

    // Atualiza uma review existente
    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable String id, @RequestBody ReviewDTO reviewDTO) {
        return reviewService.updateReview(id, reviewDTO);
    }

    // Deleta uma review por ID
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
    }

    @GetMapping("/relatorio")
    public ResponseEntity<Map<String, Object>> getRelatorio(
            @RequestParam(required = false) String periodo) {

        if (periodo != null && periodo.endsWith("dias")) {
            int dias = Integer.parseInt(periodo.replace("dias", "").trim());
            Map<String, Object> relatorio = reviewService.gerarRelatorioPorPeriodo(dias);
            return ResponseEntity.ok(relatorio);
        }

        List<ReviewDTO> reviews = reviewService.listarTodos();
        return ResponseEntity.ok(Map.of("reviews", reviews));
    }

}
