package br.com.lucastasca.ports.servidor;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.model.ServidorEfetivoModel;

public interface ServidorEfetivoPort {
    ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel);

    ServidorEfetivoModel buscarPorId(Long pesId);

    ServidorEfetivoModel atualizar(Long pesId, ServidorEfetivoModel servidorEfetivoModel);

    PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery);

    void excluir(Long pesId);
}
