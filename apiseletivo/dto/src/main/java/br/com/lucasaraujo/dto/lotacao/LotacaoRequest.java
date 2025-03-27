package br.com.lucasaraujo.dto.lotacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record LotacaoRequest(

         @JsonFormat(pattern = "dd/MM/yyyy")
         LocalDate lotacaoDataLotacao,

         @JsonFormat(pattern = "dd/MM/yyyy")
         LocalDate lotacaoDataRemocao,

         String lotacaoPortaria,

         Long pessoaId,

         Long unidadeId
         ) {
    
}
