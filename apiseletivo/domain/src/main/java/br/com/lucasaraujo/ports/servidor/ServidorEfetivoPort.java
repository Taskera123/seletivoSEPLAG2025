package br.com.lucasaraujo.ports.servidor;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.ServidorEfetivoModel;

public interface ServidorEfetivoPort {
    ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel);

    ServidorEfetivoModel buscarPorId(Long pesId);

    ServidorEfetivoModel atualizar(Long pesId, ServidorEfetivoModel servidorEfetivoModel);

    PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery);

    void excluir(Long pesId);

    PageResponse<ServidorEfetivoModel> buscarServidoresLotadosUnidade(Long unidId, PageQuery pageQuery);
}
