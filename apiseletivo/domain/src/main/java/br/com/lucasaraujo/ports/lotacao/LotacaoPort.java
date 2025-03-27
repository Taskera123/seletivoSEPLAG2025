package br.com.lucasaraujo.ports.lotacao;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.LotacaoModel;

public interface LotacaoPort {

    LotacaoModel criar(LotacaoModel lotacaoModel);

    LotacaoModel buscarPorId(Long cidId);

    LotacaoModel atualizar(Long cidId, LotacaoModel lotacaoModel);

    PageResponse<LotacaoModel> listaLotacoesPaginado(PageQuery pageQuery);

    void excluir(Long cidId);
}
