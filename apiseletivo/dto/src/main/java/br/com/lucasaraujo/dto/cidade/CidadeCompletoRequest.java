package br.com.lucasaraujo.dto.cidade;

public record CidadeCompletoRequest(
        Long cidId,
        String cidNome,
        String cidUf
) {
}
