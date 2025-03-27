package br.com.lucasaraujo.dto.servidor;

import br.com.lucasaraujo.dto.pessoa.PessoaResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ServidorTemporarioResponse(
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate servidorTemporarioDataAdmissao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate servidorTemporarioDataDemissao,
        PessoaResponse pessoaResponse
){
}
