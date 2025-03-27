package br.com.lucasaraujo.dto.servidor;

import java.util.Set;

public record ServidorEfetivoLotacaoResponse(
    String nome,
    Long idade,
    String nomeUnidade,
    Set<String> listaLinkFotos
){
}
