package br.com.lucasaraujo.dto.unidade;

import java.util.Set;

public record UnidadeRequest(
        String unidadeNome,
        String unidadeSigla,
        Set<Long> enderecoIdList
) {
}
