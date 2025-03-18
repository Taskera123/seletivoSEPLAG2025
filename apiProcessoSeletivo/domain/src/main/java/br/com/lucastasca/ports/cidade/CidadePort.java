package br.com.lucastasca.ports.cidade;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.model.CidadeModel;

public interface CidadePort {

    CidadeModel criar(CidadeModel cidadeModel);

    CidadeModel buscarPorId(Long cidId);

    CidadeModel atualizar(Long cidId, CidadeModel cidadeModel);

    PageResponse<CidadeModel> listaCidadesPaginado(PageQuery pageQuery);

    void excluir(Long cidId);
}
