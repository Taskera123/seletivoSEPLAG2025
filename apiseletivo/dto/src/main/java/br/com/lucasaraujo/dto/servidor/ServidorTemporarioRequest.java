package br.com.lucasaraujo.dto.servidor;

import br.com.lucasaraujo.dto.pessoa.PessoaRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ServidorTemporarioRequest(
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate stDataAdmissao,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate stDataDemissao,

        @JsonFormat(pattern = "dd/MM/yyyy")
        PessoaRequest pessoaRequest
) {
}
