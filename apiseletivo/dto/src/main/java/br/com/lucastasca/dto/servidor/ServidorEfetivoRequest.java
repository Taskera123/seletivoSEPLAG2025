package br.com.lucastasca.dto.servidor;

import br.com.lucastasca.dto.pessoa.PessoaRequest;

public record ServidorEfetivoRequest(
        String seMatricula,
        PessoaRequest pessoaRequest
) {
}
