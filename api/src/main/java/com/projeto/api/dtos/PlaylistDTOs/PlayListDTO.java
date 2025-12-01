package com.projeto.api.dtos.PlaylistDTOs;

import com.projeto.api.dtos.MusicaDTOs.MusicaDTO;
import com.projeto.api.dtos.MusicaDTOs.MusicaResumoDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioResumoDTO;
import com.projeto.api.models.PlayList;
import com.projeto.api.models.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class PlayListDTO {

    //Não é nescessário validar o id pois ele é gerado automaticamente quando passa pelo mapper, atraves da função id gerador
    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String nome;

    @Size(min = 0, max = 500,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "O lancamento não pode estar vazio")
    private String descricao;

    //Não é nescessário validar o usuario pois ele é pego atraves do token de autenticação
    private UsuarioResumoDTO usuario;

    //Não é nescessário validar a lista de musicas, pois uma playlist pode ser criada sem musicas
    private List<MusicaResumoDTO> musicas = new ArrayList<>();


    //Geters e Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioResumoDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResumoDTO usuario) {
        this.usuario = usuario;
    }

    public List<MusicaResumoDTO> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicaResumoDTO> musicas) {
        this.musicas = musicas;
    }

}
