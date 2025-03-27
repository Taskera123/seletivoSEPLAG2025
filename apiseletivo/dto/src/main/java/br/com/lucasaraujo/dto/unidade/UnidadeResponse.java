package br.com.lucasaraujo.dto.unidade;

import br.com.lucasaraujo.dto.endereco.EnderecoResponse;

import java.util.Set;

public record UnidadeResponse(
        Long unidadeId,
        String unidadeNome,
        String unidadeSigla,
        Set<EnderecoResponse>enderecoList
){
}
