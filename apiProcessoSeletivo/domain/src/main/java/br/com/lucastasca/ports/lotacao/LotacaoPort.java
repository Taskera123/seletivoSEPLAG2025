package br.com.lucastasca.ports.lotacao;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.model.LotacaoModel;

public interface LotacaoPort {

    LotacaoModel criar(LotacaoModel lotacaoModel);

    LotacaoModel buscarPorId(Long cidId);

    LotacaoModel atualizar(Long cidId, LotacaoModel lotacaoModel);

    PageResponse<LotacaoModel> listaLotacoesPaginado(PageQuery pageQuery);

    void excluir(Long cidId);
}
