package br.com.lucastasca.dto.servidor;

import br.com.lucastasca.dto.pessoa.PessoaResponse;

public record ServidorEfetivoResponse(
    String seMatricula,
    PessoaResponse pessoaResponse
){
}
