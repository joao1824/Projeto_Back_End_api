package com.projeto.api.controller;


import com.projeto.api.dtos.TagDTOs.TagDTO;
import com.projeto.api.service.TagService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    // Retorna todos as tags com paginação e ordenação
    @GetMapping
    public Page<TagDTO> getAllTags(@RequestParam Map<String, String> filtros, @PageableDefault(size = 10) Pageable pageable) {
        return tagService.getAllTags(filtros,pageable);
    }

    // Retorna uma tag por ID
    @GetMapping("/{id}")
    public TagDTO getTagById(@PathVariable String id){
        return tagService.getTagById(id);
    }

    // Cria uma nova tag
    @PostMapping
    public TagDTO newTag(@RequestBody TagDTO tagDTO){
        return tagService.newTag(tagDTO);
    }

    // Atualiza uma tag existente
    @PutMapping("/{id}")
    public TagDTO updateTag(@PathVariable String id, @RequestBody TagDTO tagDTO) {
        return tagService.updateTag(id, tagDTO);
    }
    // Deleta uma tag por ID
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable String id) {
        tagService.deleteTag(id);
    }

}
