package com.projeto.api.controller;


import com.projeto.api.dtos.TagDTO;
import com.projeto.api.models.Tag;
import com.projeto.api.repository.TagRepository;
import com.projeto.api.service.TagService;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    // Retorna todos as tags com paginação e ordenação
    @GetMapping
    public Page<TagDTO> getAllTags(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return tagService.getAllTags(pageable);
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
