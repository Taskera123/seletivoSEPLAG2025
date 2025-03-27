package br.com.lucasaraujo.dto.unidade;

import java.util.Set;

public record UnidadeRequest(
        String unidNome,
        String unidSigla,
        Set<Long> enderecoIdList
) {
}
