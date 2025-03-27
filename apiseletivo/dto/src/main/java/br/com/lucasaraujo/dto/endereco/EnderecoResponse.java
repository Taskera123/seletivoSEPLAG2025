package br.com.lucasaraujo.dto.endereco;

import br.com.lucasaraujo.dto.cidade.CidadeResponse;

public record EnderecoResponse(

         Long endId,
        
         String endTipoLogradouro,

         String endLogradouro,

         Integer endNumero,

         String endBairro,

         CidadeResponse cidadeResponse
) {
}
