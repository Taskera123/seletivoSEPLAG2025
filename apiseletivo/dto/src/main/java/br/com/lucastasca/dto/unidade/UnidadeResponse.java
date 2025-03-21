package br.com.lucastasca.dto.unidade;

import br.com.lucastasca.dto.endereco.EnderecoResponse;

import java.util.Set;

public record UnidadeResponse(
        Long unidId,
        String unidNome,
        String unidSigla,
        Set<EnderecoResponse>enderecoList
){
}
