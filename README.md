# LASTHEARDAPI

## Integrantes

  Bruno Pagani Rampinelli<br>
  João Henrique Camillo Fogaça

## Tema

### Geral
  <b>API dedicada à organização de artistas, álbuns e músicas. Suporte a reviews, notas e playlists enviadas por usuários. </b><br>
  (Entretenimento e Cultura)

### Como executar

- Utilize Java 17 ou Java 22
   - Recomendamos uma dessas versões para garantir a compatibilidade.

- Abra o projeto na IDE IntelliJ
   - Essa foi a IDE utilizada durante o desenvolvimento, garantindo melhor suporte e configuração automática.

- Configure as variáveis de ambiente
   - Acesse o arquivo Variaveis de ambiente.txt no repositório.

  - Copie as variáveis contidas nele.
  - Substitua no arquivo application.properties ou crie um arquivo .env com esses valores.

- Execute o projeto
   - Após configurar as variáveis e garantir a versão correta do Java, basta rodar a aplicação.
### Variaveis de Ambiente

Variaveis de ambiente se encontram no [Arquivo TXT](https://github.com/joao1824/Projeto_Back_End_api/blob/main/Variaveis%20de%20ambiente.txt)

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
   
### Tecnologias Utilizadas

Java (Rodando no 22 e 17)
Linguagem principal da aplicação.

Spring Boot 3.2.12
Framework para criação rápida de aplicações Java modernas.

Spring Web (spring-boot-starter-web)
Usado para criar APIs REST.

Spring Data JPA
Persistência de dados usando JPA/Hibernate.

Spring Validation
Para validações de dados recebidos no backend.

Spring Security
Autenticação e autorização da aplicação.

### Modelo

  <img width="750" height="590" alt="image" src="https://github.com/user-attachments/assets/476812ee-9c61-4cbc-b90f-cf2f3d2d8a28" />

### Documentação dos principais controladores e endpoints da API, incluindo operações CRUD e integrações externas.

API de gerenciamento de músicas, artistas, álbuns, playlists, reviews, tags e usuários, com integração externa ao Spotify.

Tecnologias: Java 17+, Spring Boot 3.1.4, Spring Security + JWT, JPA/Hibernate, H2 (dev/teste).

Autenticação: Bearer Token para endpoints protegidos.


## Album Controller (CRUD)

| Método | Endpoint              | Parâmetros                                    | Descrição |
|--------|---------------------|----------------------------------------------|-----------|
| GET    | `/albums/spotify`    | `nomeAlbum` (query), `nomeArtista` (query) | Busca um álbum no Spotify ou cria se não existir |
| GET    | `/albums`            | Query params: filtros opcionais, `page`, `size` | Lista todos os álbuns com paginação |
| GET    | `/albums/{id}`       | `id` (path)                                 | Retorna um álbum específico por ID |
| POST   | `/albums`            | Body: `AlbumDTO`                             | Cria um novo álbum |
| PUT    | `/albums/{id}`       | `id` (path), Body: `AlbumDTO`               | Atualiza um álbum existente |
| DELETE | `/albums/{id}`       | `id` (path)                                 | Deleta um álbum existente |

## API Controller (Spotify)

| Método | Endpoint       | Parâmetros       | Descrição |
|--------|---------------|----------------|-----------|
| GET    | `/api/artista` | `nome` (query) | Busca um artista pelo nome no Spotify |
| GET    | `/api/album`   | `nome` (query) | Busca um álbum pelo nome no Spotify |
| GET    | `/api/musica`  | `nome` (query) | Busca uma música pelo nome no Spotify |


## Artista Controller (CRUD)

| Método | Endpoint               | Parâmetros                         | Descrição |
|--------|-----------------------|-----------------------------------|-----------|
| GET    | `/artistas/spotify`   | `nome` (query)                    | Busca um artista no Spotify pelo nome ou cria se não existir |
| GET    | `/artistas`           | Query params: filtros opcionais, `page`, `size` | Lista todos os artistas com paginação |
| GET    | `/artistas/{id}`      | `id` (path)                       | Retorna um artista específico por ID |
| POST   | `/artistas`           | Body: `ArtistaDTO`                 | Cria um novo artista |
| PUT    | `/artistas/{id}`      | `id` (path), Body: `ArtistaDTO`   | Atualiza um artista existente |
| DELETE | `/artistas/{id}`      | `id` (path)                       | Deleta um artista existente |


## Musica Controller (CRUD)

| Método | Endpoint           | Parâmetros                      | Descrição |
|--------|------------------|--------------------------------|-----------|
| GET    | `/musicas`        | Query params: filtros opcionais, `page`, `size` | Lista todas as músicas com paginação |
| GET    | `/musicas/{id}`   | `id` (path)                     | Retorna uma música específica por ID |
| POST   | `/musicas`        | Body: `MusicaDTO`               | Cria uma nova música |
| PUT    | `/musicas/{id}`   | `id` (path), Body: `MusicaDTO` | Atualiza uma música existente |
| DELETE | `/musicas/{id}`   | `id` (path)                     | Deleta uma música existente |


## PlayList Controller (CRUD)

| Método | Endpoint                                      | Parâmetros                                      | Descrição |
|--------|----------------------------------------------|------------------------------------------------|-----------|
| GET    | `/playlists`                                 | Query params: filtros opcionais, `page`, `size` | Lista todas as playlists com paginação e ordenação |
| GET    | `/playlists/{id}`                            | `id` (path)                                   | Retorna uma playlist específica por ID |
| POST   | `/playlists`                                 | Body: `PlayListDTO`                            | Cria uma nova playlist |
| PUT    | `/playlists/{id_playlist}`                   | `id_playlist` (path), Body: `PlayListDTO`     | Atualiza uma playlist existente |
| POST   | `/playlists/{id_playlist}/musicas/{id_musica}` | `id_playlist` e `id_musica` (path)           | Adiciona uma música à playlist |
| DELETE | `/playlists/{id_playlist}/musicas/{id_musica}` | `id_playlist` e `id_musica` (path)           | Remove uma música da playlist |
| DELETE | `/playlists/{id}`                            | `id` (path)                                   | Deleta uma playlist existente |

## Review Controller (CRUD)

| Método | Endpoint             | Parâmetros                               | Descrição |
|--------|--------------------|-----------------------------------------|-----------|
| GET    | `/reviews`          | Query params: filtros opcionais, `page`, `size` | Lista todas as reviews com paginação e filtros |
| GET    | `/reviews/{id}`     | `id` (path)                              | Retorna uma review específica por ID |
| POST   | `/reviews`          | Body: `ReviewDTO`                         | Cria uma nova review |
| PUT    | `/reviews/{id}`     | `id` (path), Body: `ReviewDTO`           | Atualiza uma review existente |
| DELETE | `/reviews/{id}`     | `id` (path)                              | Deleta uma review existente |
| GET    | `/reviews/relatorio`| `periodo` (query, opcional)              | Retorna um relatório de reviews por período ou lista todas se não informado |


## Tag Controller (CRUD)

| Método | Endpoint       | Parâmetros                  | Descrição |
|--------|----------------|----------------------------|-----------|
| GET    | `/tags`        | Query params: filtros, `page`, `size` | Lista todas as tags com paginação e filtros opcionais |
| GET    | `/tags/{id}`   | `id` (path)                | Retorna uma tag específica por ID |
| POST   | `/tags`        | Body: `TagDTO`             | Cria uma nova tag |
| PUT    | `/tags/{id}`   | `id` (path), Body: `TagDTO` | Atualiza uma tag existente |
| DELETE | `/tags/{id}`   | `id` (path)                | Deleta uma tag existente |


## Usuario Controller (CRUD + Autenticação)

| Método | Endpoint                | Parâmetros                          | Descrição |
|--------|------------------------|------------------------------------|-----------|
| GET    | `/usuarios`             | Query params: filtros, `page`, `size` | Lista todos os usuários com paginação e filtros opcionais |
| GET    | `/usuarios/{id}`        | `id` (path)                        | Retorna um usuário específico por ID |
| POST   | `/usuarios/register`    | Body: `RegistrarDTO`                | Registra um novo usuário |
| POST   | `/usuarios/login`       | Body: `AuthenticationDTO`           | Autentica usuário e retorna token de acesso |
| PATCH  | `/usuarios/{id}/senha`  | `id` (path), Body: `SenhaNovaDTO`   | Altera a senha de um usuário |
| PATCH  | `/usuarios/{id}/email`  | `id` (path), Body: `EmailNovoDTO`   | Altera o email de um usuário |
| PATCH  | `/usuarios/{id}/nome`   | `id` (path), Body: `UsuarioNomeDTO` | Altera o nome de um usuário |
| DELETE | `/usuarios/{id}`        | `id` (path)                        | Deleta um usuário |


## Cartão Desafio

### Review Controller
| Método | Endpoint         | Parâmetros         | Descrição              |
|--------|------------------|--------------------|------------------------|
| **GET** | `/reviews/relatorio` | `periodo (query)`      | Gerar Relatório de quantas reviews tiverem em determinado tempo (em dias)  |

Endpoint que gera um **relatório de reviews** com base em um período de tempo (em dias).
Se o parâmetro `periodo` não for informado, retorna todas as reviews.



### `GET /relatorio`

| Parâmetro     | Tipo      | Obrigatório      | Descrição              |
|---------------|-----------|------------------|------------------------|
| `periodo` | `String` | Não | Define o intervalo de tempo. Formato:`"Xdias"` (ex.: `"7dias"`). |



### Exemplo

| Situação           | Exemplo de resposta         |
|--------------------|-----------------------------|
| Com período |`{"totalNoPeriodo": 12, "totalGeral": 45 }` |
| Sem período | `{ "reviews": [ ... ] }` |


---

## Autenticação

| Tipo         | Descrição                            |
|---------------|-------------------------------------|
| **Bearer Token** | Autorização para endpoints protegidos |



