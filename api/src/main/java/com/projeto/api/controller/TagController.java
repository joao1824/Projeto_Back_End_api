package com.projeto.api.controller;


import com.projeto.api.dtos.TagDTOs.TagDTO;
import com.projeto.api.service.TagService;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    // Retorna todos as tags com paginação e ordenação
    @GetMapping
    public Page<TagDTO> getAllTags(@RequestParam Map<String,String> filtros, @PageableDefault(size = 10) Pageable pageable) {
        return tagService.getAllTags(filtros,pageable);
    }

    // Retorna uma tag por ID
    @GetMapping("/{id}")
    public TagDTO getTagById(@PathVariable String id){
        return tagService.getTagById(id);
    }

    // Cria uma nova tag
    @PostMapping()
    public TagDTO novaTag(@RequestBody TagDTO tagDTO){
        return tagService.novaTag(tagDTO);
    }

    // Atualiza uma tag existente
    @PutMapping("/{id}")
    public TagDTO atualizarTag(@PathVariable String id, @RequestBody TagDTO tagDTO) {
        return tagService.atualizarTag(id, tagDTO);
    }
    // Deleta uma tag por ID

    @DeleteMapping("/{id}")
    public void deletarTag(@PathVariable String id) {
        tagService.deletarTag(id);
    }

}
