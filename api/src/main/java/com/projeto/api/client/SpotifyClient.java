package com.projeto.api.client;

// spotify-web-api-java
import se.michaelthelin.spotify.SpotifyApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SpotifyClient {

    private final SpotifyApi spotifyApi;

    public SpotifyClient(
            // clientId e clientSecret vêm da .env
            @Value("${spotify.client.id}") String clientId,
            @Value("${spotify.client.secret}") String clientSecret
    ) {
        // configura acesso à api com dependencia spotify-web-api-java
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        try {
            var credentials = spotifyApi.clientCredentials().build().execute();
            spotifyApi.setAccessToken(credentials.getAccessToken()); // cria access token pela dependencia
        } catch (Exception e) {
            throw new RuntimeException("Erro | Erro ao configurar Access Token: " + e.getMessage(), e);
        }
    }

    public SpotifyApi getApi() {
        return spotifyApi;
    }
}
