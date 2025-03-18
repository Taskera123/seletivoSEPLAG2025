package br.com.lucastasca.dto.endereco;

import br.com.lucastasca.dto.cidade.CidadeCompletoRequest;

public record EnderecoRequest(

        String endTipoLogradouro,

        String endLogradouro,

        Integer endNumero,

        String endBairro,

        CidadeCompletoRequest cidadeCompletoRequest
) {
}
