package br.com.lucasaraujo.dto.pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record PessoaRequest(
         String pessoaNome,

         @JsonFormat(pattern = "dd/MM/yyyy")
         LocalDate pessoaDataNascimento,

         String pessoaSexo,

         String pessoaMae,

         String pessoaPai,

         Set<Long>enderecoIdList
) {
}
