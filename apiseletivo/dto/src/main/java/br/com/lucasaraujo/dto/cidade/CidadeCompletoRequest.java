package br.com.lucasaraujo.dto.cidade;

public record CidadeCompletoRequest(
        Long cidadeId,
        String cidadeNome,
        String cidadeUf
) {
}
