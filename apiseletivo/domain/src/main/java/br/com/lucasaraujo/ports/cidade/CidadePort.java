package br.com.lucasaraujo.ports.cidade;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.CidadeModel;

public interface CidadePort {

    CidadeModel criar(CidadeModel cidadeModel);

    CidadeModel buscarPorId(Long cidadeId);

    CidadeModel atualizar(Long cidadeId, CidadeModel cidadeModel);

    PageResponse<CidadeModel> listaCidadesPaginado(PageQuery pageQuery);

    void excluir(Long cidadeId);
}
