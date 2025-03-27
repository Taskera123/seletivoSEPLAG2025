package br.com.lucasaraujo.dto.endereco;

import br.com.lucasaraujo.dto.cidade.CidadeCompletoRequest;

public record EnderecoRequest(

        String enderecoTipoLogradouro,

        String enderecoLogradouro,

        Integer enderecoNumero,

        String enderecoBairro,

        CidadeCompletoRequest cidadeCompletoRequest
) {
}
