package com.projeto.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projeto.api.dtos.UsuarioDTO;
import com.auth0.jwt.JWTVerifier;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class TokenService {

    private final UsuarioRepository usuarioRepository;
    @Value("${api.security.token.secret}")
    private String secret;

    public TokenService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String generateToken(UsuarioDTO usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withClaim("ultimaSenha_data", usuario.getUltima_atualizacao_senha().toString())
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro na geração de token );");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var verifcacao = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build();


            var decoded = verifcacao.verify(token);

            String email = decoded.getSubject();
            String ultima_atualizacao_senha = decoded.getClaim("ultimaSenha_data").asString();

            Usuario usuario = usuarioRepository.findUsuarioByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

            if (ultima_atualizacao_senha == null || !ultima_atualizacao_senha.equals(usuario.getUltima_atualizacao_senha().toString())) {
                throw new JWTVerificationException("Token inválido (senha atualizada)");
            }
            return email;
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
