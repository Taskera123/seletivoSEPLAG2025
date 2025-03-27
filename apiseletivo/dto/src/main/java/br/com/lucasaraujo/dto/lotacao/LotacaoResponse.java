package br.com.lucasaraujo.dto.lotacao;

import br.com.lucasaraujo.dto.pessoa.PessoaResponse;
import br.com.lucasaraujo.dto.unidade.UnidadeResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public record LotacaoResponse(
        Long lotacaoId,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate lotacaoDataLotacao,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate lotacaoDataRemocao,

        String lotacaoPortaria,

        PessoaResponse pessoa,

        UnidadeResponse unidadeResponse
) {
}
