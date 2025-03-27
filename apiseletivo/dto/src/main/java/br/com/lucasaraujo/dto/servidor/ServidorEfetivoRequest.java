package br.com.lucasaraujo.dto.servidor;

import br.com.lucasaraujo.dto.pessoa.PessoaRequest;

public record ServidorEfetivoRequest(
        String seMatricula,
        PessoaRequest pessoaRequest
) {
}
