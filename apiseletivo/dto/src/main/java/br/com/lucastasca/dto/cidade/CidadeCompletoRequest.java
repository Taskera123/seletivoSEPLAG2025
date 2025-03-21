package br.com.lucastasca.dto.cidade;

public record CidadeCompletoRequest(
        Long cidId,
        String cidNome,
        String cidUf
) {
}
