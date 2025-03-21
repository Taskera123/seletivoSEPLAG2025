package br.com.lucastasca.dto.pessoa;

import java.time.LocalDate;

public record PessoaResponse(
        Long pesId,

        String pesNome,

        LocalDate pesDataNascimento,

        String pesSexo,

        String pesMae,

        String pesPai
){
}
