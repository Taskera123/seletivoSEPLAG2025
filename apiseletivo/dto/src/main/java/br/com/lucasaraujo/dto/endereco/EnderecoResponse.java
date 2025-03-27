package br.com.lucasaraujo.dto.endereco;

import br.com.lucasaraujo.dto.cidade.CidadeResponse;

public record EnderecoResponse(

         Long enderecoId,
        
         String enderecoTipoLogradouro,

         String enderecoLogradouro,

         Integer enderecoNumero,

         String enderecoBairro,

         CidadeResponse cidadeResponse
) {
}
