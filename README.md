# Projeto_Back_End_api

## Integrantes

  Bruno Pagani Rampinelli<br>
  João Henrique Camillo Fogaça

## Tema

### Geral
  <b>API dedicada à organização de artistas, álbuns e músicas. Suporte a reviews, notas e playlists enviadas por usuários. </b><br>
  (Entretenimento e Cultura)
  
### Objetivos
  - Dispor de sistema de usuários, com suas respectivas reviews e playlists. Com capacidade de:
    - Armazenar usuários (perfil customizável, exibindo reviews e playlists adicionadas, página de estatísticas específicas);
    - Armazenar reviews (com notas, resenha e tags):
      - Reviews podem conter tags. Tags mais comuns entre usuários são atreladas a álbuns;
    - Armazenar playlists (contendo músicas selecionadas);

  - Dispor de artistas, seus álbuns e suas respectivas músicas (disponibilizando as letras originais e traduzidas):
    - Implementação por meio da API do Spotify.

  - Sistema de pesquisa com filtros:
    - Popularidade, com mais reviews positivas/negativas, quantidade de acessos, etc...

### Modelo

  <img width="750" height="590" alt="image" src="https://github.com/user-attachments/assets/476812ee-9c61-4cbc-b90f-cf2f3d2d8a28" />






### Documentação dos principais controladores e endpoints da API, incluindo operações CRUD e integrações externas.

---

## Busca de Itens por Nome (Query Params)

| Método | Endpoint       | Parâmetros     | Descrição                |
|--------|----------------|----------------|--------------------------|
| **GET** | `/api/artista` | `nome` (query) | Buscar artista pelo nome |
| **GET** | `/api/album`   | `nome` (query) | Buscar álbum pelo nome   |
| **GET** | `/api/musica`  | `nome` (query) | Buscar música pelo nome  |

---

## Tag Controller (CRUD)

| Método | Endpoint         | Parâmetros              | Descrição            |
|--------|------------------|-------------------------|----------------------|
| **GET** | `/api/tags`      | —                       | Listar todas as tags |
| **POST** | `/api/tags`      | `body (TagDTO)`         | Criar nova tag       |
| **GET** | `/api/tags/{id}` | `id (path)`             | Buscar tag por ID    |
| **PUT** | `/api/tags/{id}` | `id (path), body`       | Atualizar tag        |
| **DELETE** | `/api/tags/{id}` | `id (path)`          | Deletar tag          |

---

## Album Controller (CRUD + Integração com Spotify)

| Método | Endpoint                 |  Parâmetros                         |   Descrição                             |
|--------|--------------------------|------------------------------------|------------------------------------------|
| **GET** | `/api/albums/spotify`    | `nomeAlbum`, `nomeArtista` (query) | Buscar ou criar álbum pelo Spotify       |
| **GET** | `/api/albums`            | —                                  | Listar todos os álbuns                   |
| **POST** | `/api/albums`            | `body (AlbumDTO)`                  | Criar novo álbum                         |
| **GET** | `/api/albums/{id}`       | `id (path)`                        | Buscar álbum por ID                      |
| **PUT** | `/api/albums/{id}`       | `id (path), body`                  | Atualizar álbum                          |
| **DELETE** | `/api/albums/{id}`    | `id (path)`                        | Deletar álbum                            |

---

## Música Controller (CRUD)

| Método | Endpoint             | Parâmetros         | Descrição              |
|--------|----------------------|--------------------|------------------------|
| **GET** | `/api/musicas`       | —                  | Listar todas as músicas |
| **POST** | `/api/musicas`       | `body (MusicaDTO)` | Criar nova música      |
| **GET** | `/api/musicas/{id}`  | `id (path)`        | Buscar música por ID   |
| **PUT** | `/api/musicas/{id}`  | `id (path), body`  | Atualizar música       |
| **DELETE** | `/api/musicas/{id}` | `id (path)`      | Deletar música         |

---

## Review Controller (CRUD)

| Método | Endpoint         | Parâmetros         | Descrição              |
|--------|------------------|--------------------|------------------------|
| **GET** | `/reviews`       | —                  | Listar todas as reviews |
| **POST** | `/reviews`       | `body (ReviewDTO)` | Criar nova review      |
| **GET** | `/reviews/{id}`  | `id (path)`        | Buscar review por ID   |
| **PUT** | `/reviews/{id}`  | `id (path), body`  | Atualizar review       |
| **DELETE** | `/reviews/{id}` | `id (path)`      | Deletar review         |

---

## Usuário Controller (CRUD)

| Método | Endpoint                 | Parâmetros          | Descrição             |
|--------|--------------------------|---------------------|-----------------------|
| **GET** | `/conta/usuarios`        | —                   | Listar usuários       |
| **POST** | `/conta/usuarios`        | `body (UsuarioDTO)` | Criar novo usuário    |
| **GET** | `/conta/usuarios/{id}`   | `id (path)`         | Buscar usuário por ID |
| **PUT** | `/conta/usuarios/{id}`   | `id (path), body`   | Atualizar usuário     |
| **DELETE** | `/conta/usuarios/{id}` | `id (path)`        | Deletar usuário       |

---

## Autenticação

| Tipo         | Descrição                            |
|---------------|-------------------------------------|
| **Bearer Token** | Autorização para endpoints protegidos |






