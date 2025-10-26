package com.projeto.api.service;

import com.projeto.api.dtos.TagDTO;
import com.projeto.api.models.Tag;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.TagRepository;
import org.springframework.core.metrics.StartupStep;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;


@Service
public class TagService {

    // Repositório de Tags
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    //Retorna todos
    public Page<TagDTO> getAllTags(Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(pageable);

        if (tags.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma tag encontrada.");
        }
        return tags.map(TagDTO::new);
    }

    //Retorna por id
    public TagDTO getTagById(String id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum tag encontrado."));
        return new TagDTO(tag);
    }


    //cria nova tag
    public TagDTO novaTag(TagDTO tagDTO) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        if (!usuarioLogado.getIsAdmin()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas administradores podem criar tags.");
        }

        Tag tag = new Tag();
        tag.setNome(tagDTO.getNome());
        tag.getReviews().addAll(tagDTO.getReviews());
        tagRepository.save(tag);
        return new TagDTO(tag);
    }

    //Atualiza tag
    public TagDTO atualizarTag(String id, TagDTO tagDTO) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        if (!usuarioLogado.getIsAdmin()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas administradores podem criar tags.");
        }
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum tag encontrado."));
        tag.setNome(tagDTO.getNome());
        tagRepository.save(tag);
        return new TagDTO(tag);
    }

    //Deleta tag
    public void deletarTag(String id) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        if (!usuarioLogado.getIsAdmin()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas administradores podem criar tags.");
        }
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum tag encontrado."));
        tagRepository.delete(tag);
    }





}
