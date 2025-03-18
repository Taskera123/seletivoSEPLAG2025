package br.com.lucastasca.ports.unidade;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.model.UnidadeModel;

public interface UnidadePort {

    UnidadeModel criar(UnidadeModel unidadeModel);

    UnidadeModel buscarPorId(Long unidId);

    UnidadeModel atualizar(Long unidId, UnidadeModel unidadeModel);

    PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery);

    void excluir(Long unidId);
}
