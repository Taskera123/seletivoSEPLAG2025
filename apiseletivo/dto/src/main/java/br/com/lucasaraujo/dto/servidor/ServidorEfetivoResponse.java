package br.com.lucasaraujo.dto.servidor;

import br.com.lucasaraujo.dto.pessoa.PessoaResponse;

public record ServidorEfetivoResponse(
    String seMatricula,
    PessoaResponse pessoaResponse
){
}
