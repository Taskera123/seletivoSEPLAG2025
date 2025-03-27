package br.com.lucasaraujo.dto.lotacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record LotacaoRequest(

         @JsonFormat(pattern = "dd/MM/yyyy")
         LocalDate lotDataLotacao,

         @JsonFormat(pattern = "dd/MM/yyyy")
         LocalDate lotDataRemocao,

         String lotPortaria,

         Long pesId,

         Long unidId
         ) {
    
}
