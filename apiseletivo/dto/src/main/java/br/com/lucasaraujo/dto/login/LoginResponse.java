package br.com.lucasaraujo.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
        @JsonProperty("accessToken") String accessToken,
        @JsonProperty("refreshToken") String refreshToken
) {
}
