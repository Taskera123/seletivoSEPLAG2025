package br.com.lucasaraujo.stories.unidade;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.UnidadeModel;
import br.com.lucasaraujo.ports.unidade.UnidadePort;

public class UnidadeUseStory {

    private final UnidadePort unidadePort;

    public UnidadeUseStory(UnidadePort unidadePort) {
        this.unidadePort = unidadePort;
    }

    public UnidadeModel buscarPorId(Long unidadeId){
        return unidadePort.buscarPorId(unidadeId);
    }

    public PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery){
        return unidadePort.listaUnidadesPaginado(pageQuery);
    }

    public UnidadeModel criar(UnidadeModel unidadeModel){
        return unidadePort.criar(unidadeModel);
    }

    public UnidadeModel atualizar(Long unidadeId,UnidadeModel unidadeModel){
        return unidadePort.atualizar(unidadeId,unidadeModel);
    }

    public void excluir(Long unidadeId){
         unidadePort.excluir(unidadeId);
    }
}
