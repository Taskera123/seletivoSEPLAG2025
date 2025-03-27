package br.com.lucasaraujo.stories.lotacao;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.LotacaoModel;
import br.com.lucasaraujo.ports.lotacao.LotacaoPort;

public class LotacaoUseStory {

    private final LotacaoPort lotacaoPort;

    public LotacaoUseStory(LotacaoPort lotacaoPort) {
        this.lotacaoPort = lotacaoPort;
    }

    public LotacaoModel buscarPorId(Long lotacaoId){
        return lotacaoPort.buscarPorId(lotacaoId);
    }

    public PageResponse<LotacaoModel> listaLotacoesPaginado(PageQuery pageQuery){
        return lotacaoPort.listaLotacoesPaginado(pageQuery);
    }

    public LotacaoModel criar(LotacaoModel lotacaoModel){
        return lotacaoPort.criar(lotacaoModel);
    }

    public LotacaoModel atualizar(Long lotacaoId,LotacaoModel lotacaoModel){
        return lotacaoPort.atualizar(lotacaoId,lotacaoModel);
    }

    public void excluir(Long lotacaoId){
         lotacaoPort.excluir(lotacaoId);
    }
}
