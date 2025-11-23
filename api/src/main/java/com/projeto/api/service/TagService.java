package com.projeto.api.service;

import com.projeto.api.dtos.TagDTOs.TagDTO;
import com.projeto.api.exception.exceptions.EventIdNotFoundException;
import com.projeto.api.exception.exceptions.TagExistenteException;
import com.projeto.api.exception.exceptions.TagNotFoundException;
import com.projeto.api.exception.exceptions.UserNotAdminException;
import com.projeto.api.mapper.dtos.TagMapper;
import com.projeto.api.models.Tag;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.TagRepository;
import com.projeto.api.specification.TagSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;


@Service
public class TagService {

    // Repositório de Tags
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    //Retorna todos
    public Page<TagDTO> getAllTags(Map<String,String> filtros, Pageable pageable) {
        Specification<Tag> specification = TagSpecification.aplicarFiltros(filtros);
        Page<Tag> tags = tagRepository.findAll(specification,pageable);
        return tags.map(tagMapper::tagToTagDTO);
    }

    //Retorna por id
    public TagDTO getTagById(String id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException("Tag com id " + id + " não encontrada."));
        return tagMapper.tagToTagDTO(tag);
    }


    //cria nova tag
    public TagDTO novaTag(TagDTO tagDTO) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }

        if (tagRepository.existsByNome(tagDTO.getNome())) {
            throw new TagExistenteException("Tag com nome " + tagDTO.getNome() + " já existe.");
        }


        Tag tag = new Tag();
        tag.setNome(tagDTO.getNome());
        tagRepository.save(tag);
        return tagMapper.tagToTagDTO(tag);
    }

    //Atualiza tag
    public TagDTO atualizarTag(String id, TagDTO tagDTO) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Tag com id " + id + " não encontrada."));

        // Se o nome foi alterado, verifica duplicidade
        if (!tag.getNome().equals(tagDTO.getNome()) &&
                tagRepository.existsByNome(tagDTO.getNome())) {

            throw new TagExistenteException("Tag com nome " + tagDTO.getNome() + " já existe.");
        }

        tag.setNome(tagDTO.getNome());
        tagRepository.save(tag);
        return tagMapper.tagToTagDTO(tag);
    }

    //Deleta tag
    public void deletarTag(String id) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }

        Tag tag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException("Tag com id " + id + " não encontrada."));
        tagRepository.delete(tag);
    }





}
