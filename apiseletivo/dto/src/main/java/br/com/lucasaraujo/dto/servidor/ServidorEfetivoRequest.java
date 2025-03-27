package br.com.lucasaraujo.dto.servidor;

import br.com.lucasaraujo.dto.pessoa.PessoaRequest;

public record ServidorEfetivoRequest(
        String servidorMatricula,
        PessoaRequest pessoaRequest
) {
}
