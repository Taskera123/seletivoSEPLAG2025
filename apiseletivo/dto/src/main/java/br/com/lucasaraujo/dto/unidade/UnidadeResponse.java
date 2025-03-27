package br.com.lucasaraujo.dto.unidade;

import br.com.lucasaraujo.dto.endereco.EnderecoResponse;

import java.util.Set;

public record UnidadeResponse(
        Long unidId,
        String unidNome,
        String unidSigla,
        Set<EnderecoResponse>enderecoList
){
}
