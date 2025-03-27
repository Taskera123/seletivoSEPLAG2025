package br.com.lucasaraujo.dto.fotoPessoa;

import br.com.lucasaraujo.service.Resource;

public record FotoRequest(
        Long pesId,
        Resource foto

)
{}
