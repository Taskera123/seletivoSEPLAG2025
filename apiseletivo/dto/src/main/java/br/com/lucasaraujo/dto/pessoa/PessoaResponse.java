package br.com.lucasaraujo.dto.pessoa;

import br.com.lucasaraujo.dto.endereco.EnderecoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record PessoaResponse(
        Long pessoaId,

        String pessoaNome,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate pessoaDataNascimento,

        String pessoaSexo,

        String pessoaMae,

        String pessoaPai,

        Set<EnderecoResponse> enderecoList
){
}
