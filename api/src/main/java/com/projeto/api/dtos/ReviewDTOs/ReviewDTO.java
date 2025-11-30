package com.projeto.api.dtos.ReviewDTOs;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.dtos.TagDTOs.TagDTO;
import com.projeto.api.dtos.TagDTOs.TagResumoDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioResumoDTO;
import com.projeto.api.models.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class ReviewDTO {

    //Atributos


    //Não é nescessário validar o id pois ele é gerado automaticamente quando passa pelo mapper, atraves da função id gerador
    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Size(min = 0, max = 1000,message = "nome possui um tamanho não planejado")
    @NotBlank(message = "nome não pode estar em vazio")
    private String avaliacao;

    @Max(value = 100, message = "ID não é valido pois possui um tamanho não planejado")
    @Min(value = 0, message = "ID não é valido pois possui um tamanho não planejado")
    @NotNull
    private Integer nota;


    private LocalDateTime data;

    @NotNull(message = "O tag não pode ser nulo")
    private TagResumoDTO tag;

    @NotNull(message = "O album não pode ser nulo")
    private AlbumResumoDTO album;

    @NotNull(message = "O usuario não pode ser nulo")
    private UsuarioResumoDTO usuario;




    //Geters e Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public TagResumoDTO getTag() {
        return tag;
    }

    public void setTag(TagResumoDTO tag) {
        this.tag = tag;
    }

    public AlbumResumoDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumResumoDTO album) {
        this.album = album;
    }

    public UsuarioResumoDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResumoDTO usuario) {
        this.usuario = usuario;
    }
}
