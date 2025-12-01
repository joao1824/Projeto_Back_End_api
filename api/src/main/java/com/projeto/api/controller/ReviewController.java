package com.projeto.api.controller;


import com.projeto.api.dtos.ReviewDTOs.ReviewDTO;
import com.projeto.api.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.List;
import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    // Injeção do serviço de reviews

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Retorna todas as reviews com paginação e ordenação

    @Operation(summary = "Obter todas as reviews", description = "Retorna uma lista paginada de todas as reviews com suporte para filtros.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Reviews retornadas com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public Page<ReviewDTO> getAllReviews(@PageableDefault(size = 15) Pageable pageable,@RequestParam Map<String, String> filtros) {
        return reviewService.getAllReviews(pageable, filtros);
    }

    // Retorna uma review por ID
    @Operation(summary = "Obter review por ID", description = "Retorna uma review específica com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Review retornada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Review não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable String id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    // Novo review
    @Operation(summary = "Criar nova review", description = "Cria uma nova review com as informações fornecidas.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Review criada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<ReviewDTO> newReview(@Valid @RequestBody ReviewDTO dados) {
        ReviewDTO newReviewDTO = reviewService.newReview(dados);
        URI uri = URI.create("/reviews/" + newReviewDTO.getId());
        return ResponseEntity.created(uri).body(newReviewDTO);
    }

    // Atualiza uma review existente
    @Operation(summary = "Atualizar review", description = "Atualiza uma review existente com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Review atualizada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Review não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable String id, @Valid @RequestBody  ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewDTO));
    }

    // Deleta uma review por ID
    @Operation(summary = "Deletar review", description = "Deleta uma review específica com base no ID fornecido.")
    @ApiResponses(value =
            {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Review deletada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Review não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }



    @Operation(summary = "Obter relatório de reviews", description = "Retorna um relatório de reviews com base no período especificado.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Relatório retornado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
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
