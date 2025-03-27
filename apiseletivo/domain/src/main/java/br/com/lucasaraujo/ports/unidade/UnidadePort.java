package br.com.lucasaraujo.ports.unidade;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.UnidadeModel;

public interface UnidadePort {

    UnidadeModel criar(UnidadeModel unidadeModel);

    UnidadeModel buscarPorId(Long unidId);

    UnidadeModel atualizar(Long unidId, UnidadeModel unidadeModel);

    PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery);

    void excluir(Long unidId);
}
