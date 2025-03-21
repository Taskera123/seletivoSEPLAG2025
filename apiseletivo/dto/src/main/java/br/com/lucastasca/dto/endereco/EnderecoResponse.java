package br.com.lucastasca.dto.endereco;

import br.com.lucastasca.dto.cidade.CidadeResponse;

public record EnderecoResponse(

         Long endId,
        
         String endTipoLogradouro,

         String endLogradouro,

         Integer endNumero,

         String endBairro,

         CidadeResponse cidadeResponse
) {
}
