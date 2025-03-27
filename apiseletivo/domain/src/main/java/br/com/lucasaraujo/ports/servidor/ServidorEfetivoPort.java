package br.com.lucasaraujo.ports.servidor;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.ServidorEfetivoModel;

public interface ServidorEfetivoPort {
    ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel);

    ServidorEfetivoModel buscarPorId(Long pessoaId);

    ServidorEfetivoModel atualizar(Long pessoaId, ServidorEfetivoModel servidorEfetivoModel);

    PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery);

    void excluir(Long pessoaId);

    PageResponse<ServidorEfetivoModel> buscarServidoresLotadosUnidade(Long unidadeId, PageQuery pageQuery);
}
