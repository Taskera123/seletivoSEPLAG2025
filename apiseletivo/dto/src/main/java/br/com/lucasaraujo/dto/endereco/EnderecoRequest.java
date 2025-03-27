package br.com.lucasaraujo.dto.endereco;

import br.com.lucasaraujo.dto.cidade.CidadeCompletoRequest;

public record EnderecoRequest(

        String endTipoLogradouro,

        String endLogradouro,

        Integer endNumero,

        String endBairro,

        CidadeCompletoRequest cidadeCompletoRequest
) {
}
