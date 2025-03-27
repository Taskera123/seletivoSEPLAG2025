package br.com.lucasaraujo.dto.lotacao;

import br.com.lucasaraujo.dto.pessoa.PessoaResponse;
import br.com.lucasaraujo.dto.unidade.UnidadeResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public record LotacaoResponse(
        Long lotId,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate lotDataLotacao,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate lotDataRemocao,

        String lotPortaria,

        PessoaResponse pessoa,

        UnidadeResponse unidadeResponse
) {
}
