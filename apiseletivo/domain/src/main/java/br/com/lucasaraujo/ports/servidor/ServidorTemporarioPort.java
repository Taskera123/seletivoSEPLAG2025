package br.com.lucasaraujo.ports.servidor;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.ServidorTemporarioModel;

public interface ServidorTemporarioPort {
    ServidorTemporarioModel criar(ServidorTemporarioModel servidorTemporarioModel);

    ServidorTemporarioModel buscarPorId(Long pesId);

    ServidorTemporarioModel atualizar(Long pesId, ServidorTemporarioModel servidorTemporarioModel);

    PageResponse<ServidorTemporarioModel> listaServidoresTemporariosPaginado(PageQuery pageQuery);

    void excluir(Long pesId);
}
